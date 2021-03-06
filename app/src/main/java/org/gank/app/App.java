package org.gank.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.gank.di.component.AppComponent;
import org.gank.di.component.DaggerAppComponent;
import org.gank.di.module.AppModule;
import org.gank.di.module.NetworkModule;
import org.gank.util.LogUtil;

/**
 * Application
 * Created by Nick on 2017/1/4
 */
public class App extends Application {

    private static App sInstance;
    private AppComponent mAppComponent;

    public final static int pagesize = 10;

    @Override
    public void onCreate() {
        super.onCreate();

        App.sInstance=this;

        setupCompoent();

        LogUtil.init();

        Fresco.initialize(this);

    }

    private void setupCompoent() {
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }

    public static App getInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
