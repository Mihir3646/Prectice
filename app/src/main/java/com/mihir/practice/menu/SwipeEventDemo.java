package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

import com.mihir.practice.R;

public class SwipeEventDemo extends Activity {
    private float x1;
    private float y1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank);
    }

    // onTouchEvent () method gets called when User performs any touch event on screen
    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN: {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP: {
                float mX2 = touchevent.getX();
                float mY2 = touchevent.getY();

                //if left to right sweep event on screen
                if (x1 < mX2)
                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_SHORT).show();

                // if right to left sweep event on screen
                if (x1 > mX2)
                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_SHORT).show();

                // if UP to Down sweep event on screen
                if (y1 < mY2)
                    Toast.makeText(this, "UP to Down Swap Performed", Toast.LENGTH_SHORT).show();

                //if Down to UP sweep event on screen
                if (y1 > mY2)
                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_SHORT).show();
                break;
            }
        }
        return false;
    }
}