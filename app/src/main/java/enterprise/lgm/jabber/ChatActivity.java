package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import enterprise.lgm.jabber.R;
import enterprise.lgm.jabber.entities.Message;

public class ChatActivity extends AppCompatActivity {
    String friendname;
    JabberApplication app;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        this.app = (JabberApplication)getApplication();

        try {
            Server.getServer().getMessage("dummy", "geosch", "dummy");
        } catch (IOException e) {
            e.printStackTrace();
        }

        friendname = getIntent().getStringExtra("friendname");
        TextView textFriendName = (TextView)findViewById(R.id.friendName);
        textFriendName.setText(friendname);

        final ListView chatList = (ListView) findViewById(R.id.msgListView);
        ArrayList<Message> messages = null;
        ArrayList<String> nachrichten = new ArrayList<String>();
        try {
            messages = Server.getServer().getMessage(app.getNickname(), friendname, app.getPassword());
            for(Message m: messages){
                nachrichten.add(m.text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1, nachrichten);
        chatList.setAdapter(adapter);

        ImageButton sendMessageButton = (ImageButton) findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText messageField = (EditText) findViewById(R.id.messageEditText);
                try {
                    Server.getServer().sendMessage(app.getNickname(),friendname,messageField.getText().toString(),app.getPassword());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    messageField.setText("");
                }
            }
        });
    }
}
