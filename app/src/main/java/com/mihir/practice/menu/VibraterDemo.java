package com.mihir.practice.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mihir.practice.R;
import com.mihir.practice.VibrateService;

public class VibraterDemo extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vibrater);

        Button btn_vibrate = (Button) findViewById(R.id.btn_vibrate);

        // Set on click listener and start vibrate service when clicked on Button Vibrate
        btn_vibrate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // Create a New Intent and start the Service
                Intent intentVibrate = new Intent(getApplicationContext(), VibrateService.class);
                startService(intentVibrate);
            }
        });
    }
}
