package com.eric.rxmagfan;

import android.app.Application;

import com.eric.rxmagfan.webapi.DiComponent;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Eric on 16/5/4.
 */
public class MyApplication extends Application{

    DiComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }

}
