package enterprise.lgm.jabber;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;


import java.io.IOException;

import enterprise.lgm.jabber.JabberApplication;
import enterprise.lgm.jabber.Server;

public class TokenService extends IntentService {
    JabberApplication app;
    public TokenService() {
        super("TokenService");
    }

    @Override protected void onHandleIntent(Intent intent) {
        try { InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken("594324547505", GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            ((JabberApplication)getApplication()).sendTokenToServer(token);}
        catch (IOException e) {
            e.printStackTrace();}



    }


}
