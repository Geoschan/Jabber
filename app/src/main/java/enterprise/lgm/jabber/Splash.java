package enterprise.lgm.jabber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {
    public JabberApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (JabberApplication) getApplication();
        app.setContext(this);
        setContentView(R.layout.activity_splash2);

        final ImageView imageview = (ImageView) findViewById(R.id.imageView);
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);

        if (app.getNickname() == null && app.getPassword() == null) {
            imageview.startAnimation(animation);

            animation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    finish();
                    Intent i = new Intent(getApplicationContext(), SplashActivity.class);
                    startActivity(i);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        else
        {
            finish();
            Intent i = new Intent(getApplicationContext(), SplashActivity.class);
            startActivity(i);
        }
    }

    @Override
    protected void onStop() {
        // call the superclass method first
        super.onStop();
    }
}
