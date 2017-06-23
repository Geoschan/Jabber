package enterprise.lgm.jabber;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;


import java.io.IOException;

import static android.widget.TextView.BufferType.EDITABLE;

public class LoginActivity extends AppCompatActivity {
    public JabberApplication app;

    EditText nicknameET;
    EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (JabberApplication)getApplication();
        app.setContext(this);
        Server.getServer().setApplication(app);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginNickname).requestFocus();

        nicknameET = (EditText) findViewById(R.id.loginNickname);
        passwordET = (EditText) findViewById(R.id.loginPassword);

//        //part of auto login
//        if(app.getNickname()!=null) nicknameET.setText(app.getNickname());
//        if(app.getPassword() != null) passwordET.setText(app.getPassword());
//        login();

        if (app.getNickname() != null && app.getPassword() != null) {
            nicknameET.setText(app.getNickname());
            passwordET.setText(app.getPassword());
            login();
        }

        Button btnLogin = (Button) findViewById(R.id.btnLogin2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        }

    public void gotoSplash(View v) {
       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void login() {
        //write nickname and password to prefs via JabberApplication
        app.setNickname(nicknameET.getText().toString());
        app.setPassword(passwordET.getText().toString());

        try {
            String message = Server.getServer().login(app.getNickname(), app.getPassword());
            if(message.contains("1")){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nickname", app.getNickname());
                startActivity(intent);
                finish();
            }else if (message.contains("0")){
                AlertBuilder.alertSingleChoice("Password or Nickname are incorrect", "OK", LoginActivity.this);
            }else{
                AlertBuilder.alertSingleChoice("There is an unexpected Error with the Server", "OK", LoginActivity.this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
