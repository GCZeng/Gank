package org.gank.di.module.main;

import org.gank.di.scope.ActivityScope;
import org.gank.ui.contract.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Nick on 2017/1/7
 */
@Module
public class MainModule {

    private MainContract.View view;

    //构造方法传递View 接口的实例化对象
    public MainModule(MainContract.View view) {
        this.view = view;
    }

    //在DI容器中提供View接口的实例化对象
    @ActivityScope
    @Provides
    public MainContract.View providerView() {
        return view;
    }

}
