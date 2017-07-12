package enterprise.lgm.jabber;

import android.app.Application;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.util.ArrayList;

import enterprise.lgm.jabber.entities.Friend;


/**
 * Created by marvi on 21.06.2017.
 */


//NOT IN USE YET
//TODO make everything easily available through JabberApplication
public class JabberApplication extends Application{
    private SharedPreferences prefs;
    private Context context;
    public static ArrayList<Friend> friends = new ArrayList<Friend>();
    public int notificationId = 1;


    

    public void setContext(Context context) {
        this.context = context;
        this.prefs = context.getSharedPreferences("loginData",Context.MODE_PRIVATE);
    }

    public void setNickname(String nickname) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("nickname",nickname);
        editor.commit();
    }

    public String getNickname() {
        return prefs.getString("nickname", null);
    }

    public void setPassword(String password) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("password",password);
        editor.commit();
    }

    public String getPassword() {
        return prefs.getString("password", null);
    }

    public void clearLoginData() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();

    }

    public void sendTokenToServer(String token) {
        try {
            Server.getServer().refreshToken(this.getNickname(),this.getPassword(),token);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
