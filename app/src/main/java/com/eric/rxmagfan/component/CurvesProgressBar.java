package com.eric.rxmagfan.component;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Eric on 16/5/16.
 */
public class CurvesProgressBar extends View {

    public CurvesProgressBar(Context context) {
        super(context);
    }

    public CurvesProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CurvesProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CurvesProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
