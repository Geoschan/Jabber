package enterprise.lgm.jabber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button btnRegister = (Button) findViewById(R.id.btnLogin2);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nickname = (EditText) findViewById(R.id.registerNickname);
                String user = nickname.getText().toString();

                EditText password1 = (EditText) findViewById(R.id.loginPassword);
                String pw1 = password1.getText().toString();

                EditText password2 = (EditText) findViewById(R.id.registerPasswordRepeat);
                String pw2 = password2.getText().toString();

                //Alert if passwords are not equal
                if (!(pw1.equals(pw2))) {
                    AlertBuilder.alertSingleChoice("You did not repeat your password correctly.", "OK", RegisterActivity.this);
                }
                //Try to register user if pws are equal
                else if (pw1.equals(pw2)) {
                    try {
                        String message= Server.getServer().register(user, pw1);

                        if(message.contains("1")){
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);

                            intent.putExtra("nickname", user);
                            startActivity(intent);

                        }else if (message.contains("0")){
                            AlertBuilder.alertSingleChoice("The nickname is not available. Please try another one.", "OK", RegisterActivity.this);
                        }else{
                            AlertBuilder.alertSingleChoice("There is an unexpected Error with the Server", "OK", RegisterActivity.this);
                        }

                    } catch (IOException e) {

                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
