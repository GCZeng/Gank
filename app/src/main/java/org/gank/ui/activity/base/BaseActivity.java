package org.gank.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.gank.app.App;
import org.gank.di.component.AppComponent;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Base Activity
 * Created by Nick on 2017/1/4
 */
public abstract class BaseActivity extends AppCompatActivity {
    private CompositeSubscription mCompositeSubscription;


    protected abstract int provideContentViewId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());
        initAppComponent(App.getInstance().getAppComponent());
    }

    /**
     * add subscription, unsubscribe it when activity ondestory
     *
     * @param subscription
     */
    public void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        this.mCompositeSubscription.add(subscription);
    }

    protected abstract void initAppComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
