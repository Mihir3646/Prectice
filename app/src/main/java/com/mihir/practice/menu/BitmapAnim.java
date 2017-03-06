package com.mihir.practice.menu;

import android.app.Activity;
import android.os.Bundle;

import com.mihir.practice.DrawBitmapAnim;

public class BitmapAnim extends Activity {
    private DrawBitmapAnim mDrawBitmapAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDrawBitmapAnim = new DrawBitmapAnim(this);
        setContentView(mDrawBitmapAnim);
    }
}
