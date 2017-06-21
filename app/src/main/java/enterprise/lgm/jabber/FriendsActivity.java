package enterprise.lgm.jabber;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
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

    private String FriendNickname = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView friendList = (ListView) findViewById(R.id.friendsList);
        ArrayList<String> myList = null;
        try {
            myList = Server.getServer().listFriends(LoginActivity.nickname, LoginActivity.password);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(FriendsActivity.this, android.R.layout.simple_list_item_1, myList );
        friendList.setAdapter(adapter);

        final FloatingActionButton addFriend = (FloatingActionButton) findViewById(R.id.addFriend);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
                builder.setMessage("Please enter the nickname: ");
                builder.setCancelable(true);
                final EditText input = new EditText(FriendsActivity.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                builder.setView(input);
                builder.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                FriendNickname = input.getText().toString();
                                if(FriendNickname== null || FriendNickname == "" || FriendNickname.isEmpty()){
                                    AlertBuilder.alertSingleChoice("Please enter a nickname", "OK", FriendsActivity.this);
                                }
                                else{
                                    try {
                                        Server.getServer().addFriend(LoginActivity.nickname, LoginActivity.password, FriendNickname);//shared.getString("nickname", ""), shared.getString("password", ""), FriendNickname);
                                        adapter.notifyDataSetChanged();
                                        friendList.invalidateViews();
                                        recreate();
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
