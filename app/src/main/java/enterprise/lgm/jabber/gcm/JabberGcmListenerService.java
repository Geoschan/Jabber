package enterprise.lgm.jabber.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.google.android.gms.gcm.GcmListenerService;

import enterprise.lgm.jabber.ChatActivity;
import enterprise.lgm.jabber.JabberApplication;

public class JabberGcmListenerService extends GcmListenerService {
    @Override public void onMessageReceived(String from, Bundle data) {
        JabberApplication app = ((JabberApplication)getApplication());
        app.setContext(this);

        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("friendname", data.getString("sender"));
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        this,
                        0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setContentTitle("Neue Nachricht von " + data.getString("sender"))
                .setContentText(data.getString("preview"))
                .setContentIntent(resultPendingIntent).setAutoCancel(true);



        int mNotificationId = app.notificationId++;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, mBuilder.build());

        Intent i = new Intent("enterprise.lgm.jabber.message." + data.getString("sender"));
        sendBroadcast(i);
    }
}
