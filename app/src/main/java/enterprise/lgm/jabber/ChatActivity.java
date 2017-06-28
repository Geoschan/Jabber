package enterprise.lgm.jabber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import enterprise.lgm.jabber.R;

public class ChatActivity extends AppCompatActivity {
    String friendname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        friendname = getIntent().getStringExtra("friendname");
        TextView textFriendName = (TextView)findViewById(R.id.friendName);
        textFriendName.setText(friendname);
    }
}
