package org.gank.di.module;

import android.content.Context;

import org.gank.app.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nick on 2017/1/5
 */
@Module
public class AppModule {
    private Context mContext;

    public AppModule(App application) {
        this.mContext = application;
    }

    @Provides
    @Singleton
    Context provideAppContext() {
        return mContext;
    }
}
