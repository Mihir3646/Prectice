package com.mihir.practice.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.mihir.practice.R;
import com.mihir.practice.menu.GridViewActivity.ImageAdapter;

public class FullImageActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        // get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        GridViewActivity mGridViewActivity = new GridViewActivity();
        ImageAdapter mImageAdapter = mGridViewActivity.new ImageAdapter(this);

        ImageView imageView = (ImageView) findViewById(R.id.full_image_view);
        imageView.setImageResource(mImageAdapter.mThumbIds[position]);
    }
}