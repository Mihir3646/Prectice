package com.mihir.practice.menu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mihir.practice.R;

public class CustomeToast extends Activity {
    private static CountDownTimer timer = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inflatedToast();
        runTimeCustomToast();

        // creating toast and setting properties
        final Toast toast1 = new Toast(this);
        TextView textView = new TextView(this);
        textView.setTextColor(Color.GRAY);
        textView.setBackgroundColor(Color.TRANSPARENT);
        textView.setTextSize(20);
        textView.setText("This Toast will Display for 5 Seconds in Center of The Screen");
        toast1.setGravity(Gravity.CENTER_VERTICAL, 0, 0);

        toast1.setView(textView);

        // Create the CountDownTimer object and implement the 2 methods
        // show the toast in onTick() method and cancel the toast in onFinish() method
        // it will show the toast for 20 seconds (20000 milliseconds 1st argument) with interval of 1 second(2nd argument)
        timer = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {
                toast1.show();
            }

            public void onFinish() {
                toast1.cancel();
            }
        }.start();
    }

    private void inflatedToast() {
        LayoutInflater inflater = getLayoutInflater();
        // Inflate the Layout
        View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));

        TextView text = (TextView) layout.findViewById(R.id.textToShow);
        // Set the Text to show in TextView
        text.setText("My Custom Toast in Center of Screen");

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void runTimeCustomToast() {
        LinearLayout layout = new LinearLayout(this);
        layout.setBackgroundColor(Color.DKGRAY);

        TextView tv = new TextView(this);
        // set the TextView properties like color, size etc
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(15);

        tv.setGravity(Gravity.CENTER_VERTICAL);
        tv.setText("My Custom Toast at Bottom of Screen");

        ImageView img = new ImageView(this);
        img.setImageResource(android.R.drawable.ic_media_play);

        // add both the Views TextView and ImageView in layout
        layout.addView(img);
        layout.addView(tv);

        // context is object of Context write "this" if you are an Activity Set The layout as Toast View
        Toast toast = new Toast(this);
        toast.setView(layout);

        // Position you toast here toast position is 50 dp from bottom you can give any integral value   
        toast.setGravity(Gravity.BOTTOM, 0, 50);
        toast.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}