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

    SharedPreferences shared;
    SharedPreferences.Editor editor;
    EditText nicknameShared=null;
    EditText passwordShared=null;
    public static String nickRegistry;
    public static String nickname;
    public static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        app = (JabberApplication)getApplication();
        app.setContext(this);
        Server.getServer().setApplication(app);

        SharedPreferences shared= getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.loginNickname).requestFocus();

        //nickname übergeben
        if(getIntent().getStringExtra("nickname")!=null){
            nickRegistry= getIntent().getStringExtra("nickname");
            nicknameShared = (EditText)findViewById(R.id.loginNickname);
            nicknameShared.setText(nickRegistry, EDITABLE);
        }

        if(shared!=null && nickRegistry==null) {
            if(shared.contains("nickname")) {
                nicknameShared = (EditText)findViewById(R.id.loginNickname);
                nicknameShared.setText(shared.getString("nickname", ""), EDITABLE);
                nickname=(shared.getString("nickname", ""));
            }
            if(shared.contains("password")) {
                passwordShared = (EditText)findViewById(R.id.loginPassword);
                passwordShared.setText(shared.getString("password", ""), EDITABLE);
                password=(shared.getString("password", ""));
            }
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
        EditText sharedNickname = (EditText) findViewById(R.id.loginNickname);
        app.setNicknameInPrefs(sharedNickname.getText().toString());
        EditText sharedPassword = (EditText) findViewById(R.id.loginPassword);
        app.setPasswordInPrefs(sharedPassword.getText().toString());

        try {
            String message = Server.getServer().login(nickname, password);
            if(message.contains("1")){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("nickname", nickname);
                startActivity(intent);
            }else if (message.contains("0")){
                AlertBuilder.alertSingleChoice("Password or Nickname are incorrect", "OK", LoginActivity.this);
            }else{
                AlertBuilder.alertSingleChoice("There is an unexpected Error with the Server", "OK", LoginActivity.this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("nickname: " + app.getNicknameFromPrefs());
        System.out.println("password: " + app.getPasswordFromPrefs());
    }
}
