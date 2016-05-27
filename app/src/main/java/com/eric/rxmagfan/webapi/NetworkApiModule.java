package com.eric.rxmagfan.webapi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Eric on 16/5/4.
 */

@Module
public class NetworkApiModule {

    @Provides
    @Singleton
    public NetworkApi getNetwork(){
        return new NetworkApi();
    }

}
