package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {

    String nickname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginNickname).requestFocus();

        //nickname übergeben
       if(getIntent().getStringExtra("nickname")!=null){
            nickname= getIntent().getStringExtra("nickname");
        }
        if(nickname!=null){
           EditText nicknameText = (EditText)findViewById(R.id.loginNickname);
            nicknameText.setText(nickname, TextView.BufferType.EDITABLE);
        }

        Button btnLogin = (Button) findViewById(R.id.btnLogin2);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nickname = (EditText) findViewById(R.id.loginNickname);
                String user = nickname.getText().toString();

                EditText password = (EditText) findViewById(R.id.loginPassword);
                String pw = password.getText().toString();

                try {

                    String message = Server.getServer().login(user, pw);
                    if(message.contains("1")){
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("nickname", user);
                        startActivity(intent);
                    }else if (message.contains("0")){
                        AlertBuilder.alertSingleChoice("Password or Nickname are incorrect", "OK", LoginActivity.this);
                    }else{
                        AlertBuilder.alertSingleChoice("There is an unexpected Error with the Server", "OK", LoginActivity.this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        }

    public void gotoSplash(View v) {
        Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
        startActivity(intent);
    }
}
