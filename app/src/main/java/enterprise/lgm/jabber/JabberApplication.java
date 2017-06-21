package enterprise.lgm.jabber;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import enterprise.lgm.jabber.entities.User;

/**
 * Created by marvi on 21.06.2017.
 */


//NOT IN USE YET
//TODO make everything easily available through JabberApplication
public class JabberApplication extends Application{
    private SharedPreferences prefs;
    private Context context;
    private User user;

    public void setContext(Context context) {
        this.context = context;
    }

    public void setPrefs(SharedPreferences prefs) {
        this.prefs = prefs;
    }
}
