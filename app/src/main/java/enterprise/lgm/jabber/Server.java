package enterprise.lgm.jabber;

import android.util.Log;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

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


    public String changePassword(final String user, final String pwOld,final String pwNew) throws IOException {
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

    public String refreshToken(final String user, final String pw,final String token) throws IOException {
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

    public String sendMessage(final String userFrom,final String userTo, final String message, final String pw) throws IOException {
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

    public String getMessage(final String userFrom,final String userTo,final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                URL url = null;
                try {
                    url = new URL("http://palaver.se.paluno.uni-due.de/api/message/get");

                    String par = "{\"Username\":\"" + userFrom + "\",\"Password\":\"" + pw + "\",\"Recipient\":\"" + userTo + "\" }";
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

    public String listFriends(final String user, final String pw) throws IOException {
        final CountDownLatch latch = new CountDownLatch(1);
        final String[] registerAnswer = new String[1];

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
}

