package enterprise.lgm.jabber.entities;

import java.util.ArrayList;

/**
 * Created by marvi on 21.06.2017.
 */

public class Friend {
    public String nickname;
    public ArrayList<Message> messages;

    public Friend(String nickname, ArrayList<Message> messages) {
        this.nickname = nickname;
        //TODO get messages from shared prefs or from server
        this.messages = messages;
    }
}
