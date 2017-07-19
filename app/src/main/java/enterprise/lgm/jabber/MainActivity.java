package enterprise.lgm.jabber;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;
import java.util.ArrayList;

import enterprise.lgm.jabber.entities.Friend;
import enterprise.lgm.jabber.entities.Message;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    JabberApplication app;

    int position = 0;
    String nickname;
    public static MobileArrayAdapter adapter;
    public static ListView chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = (JabberApplication)getApplication();

        createFriends();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chatList = (ListView) findViewById(R.id.chatlist);

        ArrayList<String> contacts = new ArrayList<String>();
        for(Friend f:JabberApplication.friends){
            contacts.add(f.nickname);
        }
        adapter = new MobileArrayAdapter(this, contacts);
        chatList.setAdapter(adapter);
        chatList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int pos, long id) {
                position = pos;
                String selectedFromList =(String) (chatList.getItemAtPosition(position));
                Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
                intent.putExtra("friendname", selectedFromList);

                startActivity(intent);
            }
        });


        //nickname übergeben
        if(getIntent().getStringExtra("nickname")!=null){
            nickname= getIntent().getStringExtra("nickname");
        }
        if(nickname!=null){
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            View headerView = navigationView.getHeaderView(0);
            TextView nicknameText = (TextView) headerView.findViewById(R.id.drawerNickname);
            nicknameText.setText(nickname, TextView.BufferType.EDITABLE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        String initial = Character.toString(nickname.charAt(0)).toUpperCase();
        int color = ColorGenerator.MATERIAL.getColor(initial);
        TextDrawable iconInitial = TextDrawable.builder().buildRound(initial, color);
        NavigationView navHeaderView = (NavigationView)findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        ImageView imgView = (ImageView)headerView.findViewById(R.id.drawerIcon);
        imgView.setImageDrawable(iconInitial);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_friends) {
            Intent intent = new Intent(getApplicationContext(), FriendsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_logout) {
            app.clearLoginData();
            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(intent);
            app.setNotificationBoolean(false);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void createFriends(){
        try {
            ArrayList<String> contacts= Server.getServer().listFriends(app.getNickname(), app.getPassword());
            JabberApplication.friends.clear();
            for(String s: contacts){
                JabberApplication.friends.add(new Friend(s, app));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
