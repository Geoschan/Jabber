package enterprise.lgm.jabber;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import enterprise.lgm.jabber.entities.Message;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Lutz on 24.05.2017.
 */

public class Server {

    //making the server a singleton (allows only one instance of the class Server)
    //Server instance can be accessed from everywhere with Server.getServer()
    private static Server server;

    JabberApplication app;

    private Server() {

    }

    public static Server getServer() {
        if (Server.server == null) {
            Server.server = new Server();
        }
        return Server.server;
    }

    public void setApplication(JabberApplication app) {
        this.app = app;
    }

    //methods for server related functions
    //Profile
    public String register(final String user, final String pw) throws IOException {
        final String[] registerAnswer = new String[1];
        final CountDownLatch latch = new CountDownLatch(1);
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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

        //PROBLEM: Thread writes correct value to registerAnswer, BUT change is not visible from outside the thread
        //outside the thread the variable registerAnswer[] is not even initialized
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("registerAnswer: (OUTER) " + registerAnswer[0]);
        return registerAnswer[0];
    }

    public String login(final String user, final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }


    public String changePassword(final String user, final String pwOld, final String pwNew) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/user/password");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pwOld + "\",\"NewPassword\":\"" + pwNew + "\"}";
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    public String refreshToken(final String user, final String pw, final String token) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/user/pushtoken");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\",\"PushToken\":\"" + token + "\"}";
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    //Messages

    public String sendMessage(final String userFrom, final String userTo, final String message, final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/message/send");

                    String par = "{\"Username\":\"" + userFrom + "\",\"Password\":\"" + pw + "\",\"Recipient\":\"" + userTo + "\",\"Mimetype\":\"" + "text/plain" + "\",\"Data\":\"" + message + "\"}";
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    public ArrayList<Message> getMessage(final String user, final String friend, final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final JSONObject[] registerAnswer = new JSONObject[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/message/get");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\",\"Recipient\":\"" + friend + "\" }";
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
                    System.out.println("string " + ausgabe);
                    JSONObject object = null;
                    try {
                        object = new JSONObject(ausgabe);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    registerAnswer[0] = object;
                    try {
                        System.out.println("registerAnswer: (INNER) " + registerAnswer[0].getString("Data"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JSONArray arr = null;
        try {
            arr = registerAnswer[0].getJSONArray("Data");
            ArrayList<Message> list = new ArrayList<Message>();
            for(int i = 0; i < arr.length(); i++) {
                JSONObject object = (JSONObject) arr.get(i);
                boolean sender = false;
                if(object.getString("Sender").equals(user)){
                    sender=true;
                }
                try {
                    Message mess = new Message(object.getString("Data"), sender, object.getString("DateTime"));
                    list.add(mess);
                } catch (ParseException e) {
                    e.printStackTrace();
                };
            }
           return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }




    public String getOffsetMessage(final String userFrom,final String userTo,final String pw,final String date) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/message/getoffset");

                    String par = "{\"Username\":\"" + userFrom + "\",\"Password\":\"" + pw + "\",\"Recipient\":\"" + userTo + "\", \"OffSet\":\"" + date + "\"}";
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

                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    // Friend
    public String addFriend(final String user, final String pw,final String friend) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/friends/add");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\",\"Friend\":\"" + friend + "\"}";
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    public String removeFriend(final String user, final String pw,final String friend) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/friends/remove");

                    String par = "{\"Username\":\"" + user + "\",\"Password\":\"" + pw + "\",\"Friend\":\"" + friend + "\"}";
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
                    registerAnswer[0] = ausgabe;
                    System.out.println("registerAnswer: (INNER) " + registerAnswer[0]);
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return registerAnswer[0];
    }

    public ArrayList<String> listFriends(final String user, final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final JSONObject[] registerAnswer = new JSONObject[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/friends/get");

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
                    for (int c; (c = in.read()) >= 0; ) {
                        ausgabe += (char) c;
                    }
                    JSONObject object = null;
                    try {
                        object = new JSONObject(ausgabe);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    registerAnswer[0] = object;
                    try {
                        System.out.println("registerAnswer: (INNER) " + registerAnswer[0].getString("Data"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    latch.countDown();

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
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            JSONArray arr = registerAnswer[0].getJSONArray("Data");
            ArrayList<String> list = new ArrayList<String>();
            for(int i = 0; i < arr.length(); i++){
                list.add(arr.get(i).toString());
            }
            return list;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

