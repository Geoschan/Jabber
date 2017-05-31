package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginNickname).requestFocus();

        Button btnLogin = (Button) findViewById(R.id.btnLogin2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nickname = (EditText) findViewById(R.id.loginNickname);
                String user = nickname.getText().toString();

                EditText password = (EditText) findViewById(R.id.loginPassword);
                String pw = password.getText().toString();

                try {
                    System.out.println("cmooon "+Server.getServer().login(user, pw));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        }

    public void gotoLogin(View v) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }
}
