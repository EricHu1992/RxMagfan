package com.eric.rxmagfan.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Eric on 16/5/16.
 */
public class CustomView extends View {

    private Paint paint;

    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
    }

    int margin = 50;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        int width = canvas.getWidth();
        canvas.drawColor(Color.BLACK);
//        canvas.translate(canvas.getWidth() / 2, 0);
        canvas.drawCircle(width / 2, width / 2, width / 2 - margin, paint);

        canvas.save();

        canvas.drawText(width+"",30,30,paint);

        Path arcPath = new Path();
        arcPath.addArc(new RectF(0,0,width,width/2),-180,180);
        canvas.drawTextOnPath("http://www.android777.com", arcPath, 28, 0, paint);
        canvas.restore();
    }
}
