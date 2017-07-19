package enterprise.lgm.jabber.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import com.google.android.gms.gcm.GcmListenerService;
import com.google.android.gms.internal.zzagy;

import enterprise.lgm.jabber.ChatActivity;
import enterprise.lgm.jabber.JabberApplication;
import enterprise.lgm.jabber.R;

public class JabberGcmListenerService extends GcmListenerService {

    @Override public void onMessageReceived(String from, Bundle data) {
        JabberApplication app = ((JabberApplication) getApplication());
        app.setContext(this);
        if(ChatActivity.activity!=null) {
            zzagy.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ChatActivity.activity.updateList();
                }
            });
        }
            Intent intent = new Intent(this, ChatActivity.class);
            intent.putExtra("friendname", data.getString("sender"));


        if (app.getNotificationBoolean()) {
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            intent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.icon)
                    .setContentTitle(data.getString("sender"))
                    .setContentText(data.getString("preview"))
                    .setContentIntent(resultPendingIntent).setAutoCancel(true);


            int mNotificationId = app.notificationId++;
            NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            mNotifyMgr.notify(mNotificationId, mBuilder.build());

            Intent i = new Intent("enterprise.lgm.jabber.message." + data.getString("sender"));
            sendBroadcast(i);
        }
    }
}
