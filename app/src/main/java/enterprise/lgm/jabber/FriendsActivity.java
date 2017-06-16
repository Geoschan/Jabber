package enterprise.lgm.jabber;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private String nickname = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            System.out.println(Server.getServer().listFriends("dummy", "dummy"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView friendList = (ListView) findViewById(R.id.friendsList);
        ArrayList<String> myList = new ArrayList<String>();
        myList.add("something");
        friendList.setAdapter(new ArrayAdapter<String>(FriendsActivity.this, android.R.layout.simple_list_item_1, myList ));

        // Notificationtest
        try{
        Server.getServer().addFriend("dummy", "dummy", "Geosch");

            Server.getServer().sendMessage("dummy","Geosch","bla","abc");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Server.getServer().notificationGenerator(FriendsActivity.this,"You've got a new message from "+"Geosch","bla");

        final FloatingActionButton addFriend = (FloatingActionButton) findViewById(R.id.addFriend);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
                builder.setMessage("Please enter the nickname: ");
                builder.setCancelable(true);
                final EditText input = new EditText(FriendsActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                nickname = input.getText().toString();
                                if(nickname== null || nickname == "" || nickname.isEmpty()){
                                    AlertBuilder.alertSingleChoice("Please enter a nickname", "OK", FriendsActivity.this);
                                }
                                else{
                                    try {
                                        Server.getServer().addFriend("dummy", "dummy", "figlero");

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                builder.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alertRepeat = builder.create();
                alertRepeat.show();

            }
        });
    }

}
