package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    JabberApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TokenService tokenService = new TokenService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        app = (JabberApplication) getApplication();
        app.setContext(this);

        //part of auto login
        if (app.isConnectingToInternet()) {
            try {
                String message = Server.getServer().login(app.getNickname(), app.getPassword());
                if (message.contains("\"MsgType\":1")) {
                    if (app.getNickname() != null && app.getPassword() != null) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void gotoLogin(View v) {


        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);

    }

    public void gotoRegister(View v) {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}
