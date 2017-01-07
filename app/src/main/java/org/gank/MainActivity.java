package org.gank;

import android.os.Bundle;
import android.widget.TextView;

import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;
import org.gank.di.component.AppComponent;
import org.gank.di.module.main.MainModule;
import org.gank.presenter.main.MainPresenter;
import org.gank.ui.activity.base.BaseActivity;
import org.gank.ui.contract.MainContract;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.textview) TextView textview;

    @Inject MainPresenter mainPresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        mainPresenter.getGankData();

    }

    @Override
    protected void initAppComponent(AppComponent appComponent) {
        appComponent.getMainComponent(new MainModule(this)).inject(this);
    }

    @Override
    public void showMsg(GankDataModel gankDataModel) {
        List<GankData> list = gankDataModel.getResults();
        StringBuilder sb = new StringBuilder();

        for(GankData gankData : list){
            sb.append(gankData.getDesc());
            sb.append("\n");
        }

        textview.setText(sb.toString());
    }
}
