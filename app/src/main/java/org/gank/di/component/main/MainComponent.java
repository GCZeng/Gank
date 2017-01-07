package org.gank.di.component.main;

import org.gank.MainActivity;
import org.gank.di.module.main.MainModule;
import org.gank.di.scope.ActivityScope;

import dagger.Subcomponent;

/**
 * Created by Nick on 2017/1/7
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity activity);
}
