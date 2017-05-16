package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void gotoLogin(View v)
    {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }
}
