package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                register("tf", "fsfscstest");
            }
        });
    }

    public void register(String user, String pw){

        Server s = new Server();
        try {
            if(s.register(user, pw)==200){
                System.out.println("erfolgreich registriert");
            }else{
                System.out.println("registrierung nicht erfolgreich");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
