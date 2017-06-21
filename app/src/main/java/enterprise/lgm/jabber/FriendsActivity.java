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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private String FriendNickname = "";
    int position = 0;


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

        friendList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           int pos, long id) {
                // TODO Auto-generated method stub
                        position = pos;
                        AlertDialog.Builder builder = new AlertDialog.Builder(FriendsActivity.this);
                        builder.setMessage("Do you want to delete this friend?");
                        builder.setCancelable(true);
                        builder.setPositiveButton(
                                "OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        String selectedFromList =(String) (friendList.getItemAtPosition(position));
                                        try {
                                            Server.getServer().removeFriend(LoginActivity.nickname, LoginActivity.password, selectedFromList);
                                            adapter.notifyDataSetChanged();
                                            friendList.invalidateViews();
                                            recreate();
                                            Toast.makeText(FriendsActivity.this, "Friend succesfully deleted!",
                                                    Toast.LENGTH_LONG).show();
                                        } catch (IOException e) {
                                            e.printStackTrace();
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




                return true;
            }
        });

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
                                        Toast.makeText(FriendsActivity.this, "Friend succesfully added!",
                                                Toast.LENGTH_LONG).show();
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
