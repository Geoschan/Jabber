package enterprise.lgm.jabber;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {


    // Beispiel für Notification
    protected void notificationGenerator()
    {
        Intent intent = new Intent(this, RegisterActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, 0);
        //Icon fehlt mir
        Notification n  = new Notification.Builder(this).
                setContentTitle("The registration was successful ")
                .setContentText("")
                .setContentIntent(pIntent)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.icon).build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, n);
    }

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
                        notificationGenerator();
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
