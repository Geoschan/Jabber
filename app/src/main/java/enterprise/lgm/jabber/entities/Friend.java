package enterprise.lgm.jabber.entities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import enterprise.lgm.jabber.JabberApplication;
import enterprise.lgm.jabber.Server;

/**
 * Created by marvi on 21.06.2017.
 */

public class Friend {
    public String nickname;
    public ArrayList<Message> messages =  new ArrayList<Message>();
    JabberApplication app;

    public Friend(String nickname, JabberApplication App) {
        app = App;
        this.nickname = nickname;
        try {
            JSONArray array = Server.getServer().getMessage(app.getNickname(),nickname, app.getPassword());
           for(int i= 0; i<array.length();i++){
               try {
                   JSONObject object = array.getJSONObject(i);
                   messages.add(new Message(object.getString("Data")));
               } catch (JSONException e) {
                   e.printStackTrace();
               }
           }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
