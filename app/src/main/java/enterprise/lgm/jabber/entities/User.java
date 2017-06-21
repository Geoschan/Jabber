package enterprise.lgm.jabber.entities;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import enterprise.lgm.jabber.LoginActivity;

/**
 * Created by marvi on 21.06.2017.
 */

public class User {
    public static String nickname;
    public static ArrayList<Friend> friends;

    public User (String nickname, ArrayList<Friend> friends) {
        this.nickname = nickname;
        //TODO get ArrayList friends from Server
        //TODO check for internet connection before and fill list from shared preferences if no con available
        this.friends = friends;
    }
}
