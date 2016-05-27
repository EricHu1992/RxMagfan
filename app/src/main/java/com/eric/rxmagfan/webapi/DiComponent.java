package com.eric.rxmagfan.webapi;

import com.eric.rxmagfan.view.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Eric on 16/5/4.
 */

@Singleton
@Component(modules = {NetworkApiModule.class})
public interface DiComponent {

    void inject(MainActivity activity);
}
