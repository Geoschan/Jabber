package enterprise.lgm.jabber.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

import enterprise.lgm.jabber.TokenService;

public class JabberInstanceIDListenerService extends InstanceIDListenerService {
    @Override public void onTokenRefresh() {
        Intent msgIntent = new Intent(this, TokenService.class);
        startService(msgIntent);
    }
}
