package enterprise.lgm.jabber.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marvi on 21.06.2017.
 */

public class Message {
    public String text;
    public boolean sender;
    public String date;

    public Message (String text){//, Friend sender, Date date) {
        //TODO fill with information from shared prefs or from server
        this.text = text;
       // this.sender = sender;
       // this.date = date;
    }

    public Message (String text, boolean sender, String date) throws ParseException {
        this.text = text;
       this.date = parse(date);
        this.sender = sender;
    }

    public static String parse( String input ) throws java.text.ParseException {
        String[] parts = input.split("T");
        String datum = parts[0];
        String uhrzeit = parts[1];
        String uhrzeit2 = uhrzeit.substring(0,5);
        String gesamt =datum + " um: "+ uhrzeit2;
       return gesamt;
    }
}
