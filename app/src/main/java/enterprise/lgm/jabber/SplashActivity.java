package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class SplashActivity extends AppCompatActivity {

    JabberApplication app;
    private static final int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TokenService tokenService = new TokenService();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        app = (JabberApplication)getApplication();
        app.setContext(this);
        if(checkPlayServices())
        {
            Intent msgIntent = new Intent(this, TokenService.class);
            startService(msgIntent);
        }
        //part of auto login
        if (app.getNickname() != null && app.getPassword() != null) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void gotoLogin(View v)
    {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    public void gotoRegister(View v)
    {
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private boolean checkPlayServices() {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, PLAY_SERVICES_RESOLUTION_REQUEST)
                        .show();
            } else {
                AlertBuilder.alertSingleChoice("This device is not supported by Google Play Services.", "OK", SplashActivity.this);
                finish();
            }
            return false;
        }
        return true;
    }
    }
