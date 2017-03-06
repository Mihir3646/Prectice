package com.mihir.practice.menu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mihir.practice.R;

import java.io.IOException;
import java.io.InputStream;

public class Camera extends Activity implements android.view.View.OnClickListener {
    private ImageView iv_background = null;
    private Bitmap mBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        iv_background = (ImageView) findViewById(R.id.iv_background);
        ImageButton mIb_take_pic = (ImageButton) findViewById(R.id.ib_take_pic);
        Button mBtn_setwallpaper = (Button) findViewById(R.id.btn_setwallpaper);

        mIb_take_pic.setOnClickListener(this);
        mBtn_setwallpaper.setOnClickListener(this);

        InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
        mBitmap = BitmapFactory.decodeStream(is);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_take_pic:
                Intent mIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(mIntent, 0);
                break;
            case R.id.btn_setwallpaper:
                try {
                    getApplicationContext().setWallpaper(mBitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle mBundle = data.getExtras();
            mBitmap = (Bitmap) mBundle.get("data");
            iv_background.setImageBitmap(mBitmap);
        }
    }
}
