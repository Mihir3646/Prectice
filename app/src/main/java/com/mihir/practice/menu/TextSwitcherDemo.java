package com.mihir.practice.menu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

import com.mihir.practice.R;

public class TextSwitcherDemo extends Activity {
    private TextSwitcher ts_switcher = null;

    // Array of String to Show In TextSwitcher
    private String textToShow[] = {"Count Down....", "Start...", "Its 3", "2...", "1...", "Yeh.... :)"};
    // to keep current Index of text
    private int currentIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_switcher);

        // get The references
        Button mBtn_next = (Button) findViewById(R.id.btn_next);
        ts_switcher = (TextSwitcher) findViewById(R.id.ts_switcher);

        // Set the ViewFactory of the TextSwitcher that will create TextView object when asked
        ts_switcher.setFactory(new ViewFactory() {
            public View makeView() {
                // create new textView and set the properties like color, size etc
                TextView myText = new TextView(TextSwitcherDemo.this);
                myText.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
                myText.setTextSize(36);
                myText.setTextColor(Color.BLUE);
                return myText;
            }
        });

        // Declare the in and out animations and initialize them
        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        // set the animation type of textSwitcher
        ts_switcher.setInAnimation(in);
        ts_switcher.setOutAnimation(out);

        // When clicked on Button TextSwitcher will switch between text.
        // The current Text will go OUT and next text will come in with specified animation.
        mBtn_next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                currentIndex++;
                // If index reaches maximum reset it
                if (currentIndex == textToShow.length)
                    currentIndex = 0;
                ts_switcher.setText(textToShow[currentIndex]);
            }
        });
    }
}