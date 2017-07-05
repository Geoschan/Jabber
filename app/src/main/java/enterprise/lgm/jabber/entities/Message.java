package enterprise.lgm.jabber.entities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marvi on 21.06.2017.
 */

public class Message {
    public String text;
    public Friend sender;
    public Date date;

    public Message (String text){//, Friend sender, Date date) {
        //TODO fill with information from shared prefs or from server
        this.text = text;
       // this.sender = sender;
       // this.date = date;
    }

    public Message (String text, Friend sender, String date) throws ParseException {
        this.text = text;
        this.date = parse(date);
    }

    public static Date parse( String input ) throws java.text.ParseException {

        //NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which breaks
        //things a bit.  Before we go on we have to repair this.
        SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ssz" );

        //this is zero time so we need to add that TZ indicator for
        if ( input.endsWith( "Z" ) ) {
            input = input.substring( 0, input.length() - 1) + "GMT-00:00";
        } else {
            int inset = 6;

            String s0 = input.substring( 0, input.length() - inset );
            String s1 = input.substring( input.length() - inset, input.length() );

            input = s0 + "GMT" + s1;
        }

        return df.parse( input );

    }
}
