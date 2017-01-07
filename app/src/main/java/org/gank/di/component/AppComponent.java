package org.gank.di.component;

import android.content.Context;

import org.gank.di.component.main.MainComponent;
import org.gank.di.module.AppModule;
import org.gank.di.module.NetworkModule;
import org.gank.di.module.main.MainModule;
import org.gank.service.ApiService;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Dagger App Component
 * Created by Nick on 2017/1/4
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {
    Context context();

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

    ApiService getApiService();


    //Activity
    MainComponent getMainComponent(MainModule mainModule);
}
