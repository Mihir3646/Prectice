package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.mihir.practice.R;

public class AnimationViews extends Activity implements AnimationListener, OnClickListener {
    private TextView tv_fade_in = null;
    private TextView tv_fade_out = null;
    private TextView tv_cross_fadding = null;

    // Animation
    private Animation animFadeIn, animFadeOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);

        TextView mTv_blink = (TextView) findViewById(R.id.tv_blink);
        TextView mTv_bounce = (TextView) findViewById(R.id.tv_bounce);
        tv_fade_in = (TextView) findViewById(R.id.tv_fade_in);
        tv_fade_out = (TextView) findViewById(R.id.tv_fade_out);
        TextView mTv_move = (TextView) findViewById(R.id.tv_move);
        TextView mTv_rotate = (TextView) findViewById(R.id.tv_rotate);
        TextView mTv_sequential = (TextView) findViewById(R.id.tv_sequential);
        TextView mTv_slide_up = (TextView) findViewById(R.id.tv_slide_up);
        TextView mTv_slide_down = (TextView) findViewById(R.id.tv_slide_down);
        TextView mTv_together = (TextView) findViewById(R.id.tv_together);
        TextView mTv_zoom_in = (TextView) findViewById(R.id.tv_zoom_in);
        TextView mTv_zoom_out = (TextView) findViewById(R.id.tv_zoom_out);
        tv_cross_fadding = (TextView) findViewById(R.id.tv_cross_fadding);

        mTv_blink.setOnClickListener(this);
        mTv_bounce.setOnClickListener(this);
        tv_fade_in.setOnClickListener(this);
        tv_fade_out.setOnClickListener(this);
        mTv_move.setOnClickListener(this);
        mTv_rotate.setOnClickListener(this);
        mTv_sequential.setOnClickListener(this);
        mTv_slide_up.setOnClickListener(this);
        mTv_slide_down.setOnClickListener(this);
        mTv_together.setOnClickListener(this);
        mTv_zoom_in.setOnClickListener(this);
        mTv_zoom_out.setOnClickListener(this);
        tv_cross_fadding.setOnClickListener(this);

        // load the animation
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);

        // set animation listener
        animFadeIn.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_blink:

                break;
            case R.id.tv_bounce:

                break;
            case R.id.tv_fade_in:
                tv_fade_in.startAnimation(animFadeIn);
                break;
            case R.id.tv_fade_out:
                tv_fade_out.startAnimation(animFadeOut);
                break;
            case R.id.tv_move:

                break;
            case R.id.tv_rotate:

                break;
            case R.id.tv_sequential:

                break;
            case R.id.tv_slide_up:

                break;
            case R.id.tv_slide_down:

                break;
            case R.id.tv_together:

                break;
            case R.id.tv_zoom_in:

                break;
            case R.id.tv_zoom_out:

                break;
            case R.id.tv_cross_fadding:
                // start fade in animation
                tv_cross_fadding.startAnimation(animFadeIn);

                // start fade out animation
                tv_cross_fadding.startAnimation(animFadeOut);
                break;
        }
    }

    @Override
    public void onAnimationEnd(android.view.animation.Animation animation) {
        // Take any action after completing the animation

        // check for fade in animation
        if (animation == animFadeIn) {
            Toast.makeText(getApplicationContext(), "Animation Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onAnimationRepeat(android.view.animation.Animation animation) {
    }

    @Override
    public void onAnimationStart(android.view.animation.Animation animation) {
    }
}