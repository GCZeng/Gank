package org.gank;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;
import org.gank.di.component.AppComponent;
import org.gank.di.module.main.MainModule;
import org.gank.presenter.main.MainPresenter;
import org.gank.ui.activity.base.BaseActivity;
import org.gank.ui.contract.MainContract;
import org.gank.util.LogUtil;
import org.gank.util.ToastUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.srl_layout) SwipeRefreshLayout srl_layout;
    @BindView(R.id.rv_list) RecyclerView rv_list;

    @Inject MainPresenter mainPresenter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

//        srl_layout.setColorScheme(R.color.color1, R.color.color2,R.color.color3, R.color.color4);
        srl_layout.setOnRefreshListener(()->{
            ToastUtil.showShort("刷新");
            srl_layout.setRefreshing(false);
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        srl_layout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
    }

    @Override
    protected void initData() {
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

        LogUtil.d(sb.toString());

    }
}
