package enterprise.lgm.jabber;

import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Created by Lutz on 24.05.2017.
 */

public class Server {

    //making the server a singleton (allows only one instance of the class Server)
    //Server instance can be accessed from everywhere with Server.getServer()
    private static Server server;

    private Server() {

    }

    public static Server getServer() {
        if (Server.server == null) {
            Server.server = new Server();
        }
        return Server.server;
    }


    //methods for server related functions

    public String register(final String user, final String pw) throws IOException {
        final String[] answer = new String[1];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/user/register");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\"}";
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(par);
                    writer.flush();
                    //  Log.v("", "" + conn.getResponseCode());

                    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String ausgabe = "";
                    for (int c; (c = in.read()) >= 0; )
                        ausgabe += (char) c;
                    System.out.println(ausgabe);
                    answer[0] = ausgabe;

                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (ProtocolException e1) {
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });
        t.start();

        return answer[0];
    }


    public int login(final String user, final String pw) throws IOException {
        final int[] answer = new int[1];
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/user/validate");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\"}";
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
                    writer.write(par);
                    writer.flush();
                    //  Log.v("", "" + conn.getResponseCode());

                    Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                    String ausgabe = "";
                    for (int c; (c = in.read()) >= 0; )
                        ausgabe += (char) c;
                    System.out.println(ausgabe);


                    answer[0] = in.read();
                    System.out.println("in read ist: " + answer[0]);

                } catch (UnsupportedEncodingException e1) {
                    e1.printStackTrace();
                } catch (ProtocolException e1) {
                    e1.printStackTrace();
                } catch (MalformedURLException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });
        t.start();

        return answer[0];
    }
}

