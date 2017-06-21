package enterprise.lgm.jabber.entities;

import java.util.Date;

/**
 * Created by marvi on 21.06.2017.
 */

public class Message {
    public String text;
    public Friend sender;
    public Date date;

    public Message (String text, Friend sender, Date date) {
        //TODO fill with information from shared prefs or from server
        this.text = text;
        this.sender = sender;
        this.date = date;
    }
}
