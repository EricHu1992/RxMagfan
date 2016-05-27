package com.eric.rxmagfan.view;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Eric on 16/5/4.
 */
public class BaseActivity extends FragmentActivity {

    public void justStartActivity(Class<?> cls){
        Intent intent = new Intent();
        intent.setClass(this,cls);
        startActivity(intent);
    }

}
