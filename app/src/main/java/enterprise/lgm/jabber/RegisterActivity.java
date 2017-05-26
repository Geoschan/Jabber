package enterprise.lgm.jabber;

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

                if (pw1.equals(pw2)) {
                    try {
                        Server.getServer().register(user, pw1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
    }

    protected void method() {

    }
}
