package org.gank.ui.contract;

import org.gank.data.entity.GankData;
import org.gank.data.model.GankDataModel;

/**
 * Created by Nick on 2017/1/7
 */
public class MainContract {
    public interface View {

        void showMsg(GankDataModel gankDataModel);

    }

    public interface Presenter {
        GankData getGankData();
    }

}
