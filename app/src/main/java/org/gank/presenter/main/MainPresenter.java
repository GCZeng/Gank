package org.gank.presenter.main;

import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;
import org.gank.presenter.base.BasePresenter;
import org.gank.service.ApiService;
import org.gank.ui.contract.MainContract;

import javax.inject.Inject;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Nick on 2017/1/7
 */
public class MainPresenter extends BasePresenter implements MainContract.Presenter {
    private MainContract.View view;

    @Inject ApiService apiService;

    @Override
    public GankData getGankData() {
        apiService.getGankData("休息视频", 10, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GankDataModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(GankDataModel gankDataModel) {
                        view.showMsg(gankDataModel);
                    }
                });

        return null;
    }

    @Inject
    public MainPresenter(MainContract.View view) {
        this.view = view;
    }
}
