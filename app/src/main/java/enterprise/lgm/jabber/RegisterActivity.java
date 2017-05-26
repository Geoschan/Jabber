package enterprise.lgm.jabber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText nickname = (EditText) findViewById(R.id.registerNickname);
                String user = nickname.getText().toString();

                EditText password1 = (EditText) findViewById(R.id.registerPassword);
                String pw1 = password1.getText().toString();

                EditText password2 = (EditText) findViewById(R.id.registerPasswordRepeat);
                String pw2 = password2.getText().toString();

                //Alert if passwords are not equal
                if(!(pw1.equals(pw2))) {
                    AlertBuilder.alertSingleChoice("You did not repeat your password correctly.", "OK", RegisterActivity.this);
                }
                //Try to register user if pws are equal
                else if (pw1.equals(pw2)) {
                    try {
                        Server.getServer().register(user, pw1);
                        //TODO make Thread return correct string

//                        System.out.println("temp: " + temp);
//                        String temp2 = getString(R.string.userAlreadyExists);
//                        System.out.println("temp2: " + temp2);
//
//                        if(temp.contains(temp2)) {
//                            AlertBuilder.alertSingleChoice(getString(R.string.userAlreadyExists), "OK", RegisterActivity.this);
//                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }
}
