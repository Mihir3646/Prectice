package com.mihir.practice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * @author Mihir
 * @description: this is practice App for small Apps
 */
public class Main extends Activity {
    //MediaPlayer object for play music in background
    //	private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.main);

        //Assign the resource to MediaPlayer
        //		mPlayer = MediaPlayer.create(this, R.raw.slide_sound);
        //		mPlayer.start();

        //Thread for do task after some time duration
        Thread timer = new Thread() {
            public void run() {
                try {
                    //sleep thread for time duration
                    sleep(1500);
                    //starting Sound
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //stop Sound
                    //					mPlayer.stop();

                    //Start new intent(Activity)
                    Intent menu = new Intent(Main.this, MenuActivity.class);
                    startActivity(menu);
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //		mPlayer.release();
        finish();
    }
}
