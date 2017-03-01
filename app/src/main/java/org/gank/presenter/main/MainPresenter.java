package org.gank.presenter.main;

import org.gank.app.App;
import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;
import org.gank.presenter.base.BasePresenter;
import org.gank.service.ApiService;
import org.gank.ui.adapter.HomeAdapter;
import org.gank.ui.contract.MainContract;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nick on 2017/1/7
 */
public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Inject ApiService apiService;

    private int mPage = 1;

    private List<GankData> mHomeList = null;
    private HomeAdapter mHomeAdapter = null;

    @Override
    public void loadGankData(boolean clean) {
        Observable.zip(apiService.getPicList(App.pagesize, mPage),
                apiService.getVideoList(App.pagesize, mPage),
                this::createHomeData)
                .map(gankDataModel -> gankDataModel.getResults())
                .flatMap(Observable::from)
                .toSortedList((gankData, gankData2) -> gankData2.getPublishedAt().compareTo(gankData.getPublishedAt()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GankData>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GankData> gankDatas) {
                        if (clean) mHomeList.clear();
                        mHomeList.addAll(gankDatas);
                        mHomeAdapter.notifyDataSetChanged();
                        view.refreshComplete();
                    }
                });
    }

    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;

        mHomeList = new ArrayList<>();
        mHomeAdapter = new HomeAdapter(mHomeList);
        view.setAdapter(mHomeAdapter);
    }

    private GankDataModel createHomeData(GankDataModel picDataModel, GankDataModel videoDataModel) {
        for (int i = 0; i < picDataModel.getResults().size(); i++) {
            GankData picData = picDataModel.getResults().get(i);
            GankData videoData = videoDataModel.getResults().get(i);

            picData.setDesc(picData.getDesc() + " " + videoData.getDesc());
        }
        return picDataModel;
    }
}
