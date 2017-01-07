package org.gank.service;

import org.gank.data.model.GankDataModel;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Nick on 2017/1/6
 */
public interface ApiService {
    @GET("data/{type}/{pagesize}/{page}")
    Observable<GankDataModel> getGankData(@Path("type") String type, @Path("pagesize") int pagesize, @Path("page") int page);
}
