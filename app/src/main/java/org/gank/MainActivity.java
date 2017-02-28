package org.gank;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;

import com.google.gson.Gson;

import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;
import org.gank.di.component.AppComponent;
import org.gank.di.module.main.MainModule;
import org.gank.presenter.main.MainPresenter;
import org.gank.ui.activity.base.BaseActivity;
import org.gank.ui.adapter.HomeAdapter;
import org.gank.ui.adapter.decoration.HomeItemDecoration;
import org.gank.ui.contract.MainContract;
import org.gank.util.LogUtil;
import org.gank.util.ToastUtil;
import org.gank.widget.refreshlist.RefreshList;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.rl_list) RefreshList rl_list;
    @BindView(R.id.rv_list) RecyclerView rv_list;

    @Inject MainPresenter mainPresenter;

    private HomeAdapter mHomeAdapter = null;
    private List<GankData> mHomeList = null;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);

//        rl_list.setColorScheme(R.color.color1, R.color.color2,R.color.color3, R.color.color4);
        rl_list.refresh(() -> {
            ToastUtil.showShort("刷新");
            mainPresenter.getGankData();
        });

        // 这句话是为了，第一次进入页面的时候显示加载进度条
        rl_list.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));

        //设置布局管理器
        rv_list.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        //设置adapter
        mHomeList = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeList);
        rv_list.setHasFixedSize(true);
        rv_list.setAdapter(mHomeAdapter);

        //间隔
        rv_list.addItemDecoration(new HomeItemDecoration(this));

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initAppComponent(AppComponent appComponent) {
        appComponent.getMainComponent(new MainModule(this)).inject(this);
    }

    @Override
    public void showMsg(GankDataModel gankDataModel) {
        List<GankData> list = gankDataModel.getResults();
        StringBuilder sb = new StringBuilder();

        Gson gson = new Gson();
        LogUtil.json(gson.toJson(gankDataModel));

        for (GankData gankData : list) {
            sb.append(gankData.getDesc());
            sb.append("\n");
        }

        LogUtil.d(sb.toString());

        if(list!=null){
            mHomeList.addAll(list);
            mHomeAdapter.notifyDataSetChanged();
        }

        rl_list.refreshComplete();

    }
}
