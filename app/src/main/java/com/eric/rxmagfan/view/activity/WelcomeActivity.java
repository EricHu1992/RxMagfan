package com.eric.rxmagfan.view.activity;

import android.os.Bundle;
import android.view.KeyEvent;

import com.eric.myframework.utils.PreferencesUtils;
import com.eric.rxmagfan.R;
import com.eric.rxmagfan.config.PreferencesKey;
import com.eric.rxmagfan.view.BaseActivity;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 欢迎页
 * 处理跳转到引导页还是主页逻辑
 * Created by Eric on 16/5/4.
 */
public class WelcomeActivity extends BaseActivity {

    private PreferencesUtils preferencesUtils;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        preferencesUtils = new PreferencesUtils(this);
        waitAMoment();
    }

    private void waitAMoment() {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                boolean firstTime = preferencesUtils.getBoolean(PreferencesKey.FIRST_TIME, true);
                subscriber.onNext(firstTime);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .delay(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean firstTime) {
                        if (firstTime) {
                            goGuidePage();
                        } else {
                            goMainPage();
                        }
                        finish();
                    }
                });
    }

    private void goGuidePage() {
        justStartActivity(GuideUseActivity.class);
        preferencesUtils.putBoolean(PreferencesKey.FIRST_TIME, false);
    }

    private void goMainPage() {
        justStartActivity(MainActivity.class);
    }

    /**
     * 屏蔽返回键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
