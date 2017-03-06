package com.mihir.practice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Random;

public class DrawBitmapAnim extends View {
    private Bitmap bitBall;
    private int changingY = 0;
    private int changingX = 0;

    public DrawBitmapAnim(Context context) {
        super(context);
        bitBall = BitmapFactory.decodeResource(getResources(), R.drawable.pic4);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitBall, changingX, changingY, null);
        //		canvas.drawBitmap(bitBall, (canvas.getWidth()/2), changingY, null);

        if (changingY > canvas.getHeight() || changingX > canvas.getWidth()) {
            changingY = 0;
            changingX = changingX + new Random().nextInt(canvas.getWidth());
        }

        if (changingY < canvas.getHeight()) {
            changingY += 1;
        } else {
            changingY = 0;
        }

        if (changingX < canvas.getWidth()) {
            changingX += 2;
        } else {
            changingX = 0;
        }

        Paint ourColor = new Paint();
        ourColor.setColor(Color.BLACK);
        canvas.drawRect(0, 400, canvas.getWidth(), 550, ourColor);
        canvas.drawBitmap(bitBall, changingY, changingX, null);

        invalidate();
    }
}
