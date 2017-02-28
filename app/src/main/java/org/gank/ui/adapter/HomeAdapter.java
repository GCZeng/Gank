package org.gank.ui.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import org.gank.R;
import org.gank.data.entity.GankData;
import org.gank.ui.adapter.base.BaseAdapter;
import org.gank.util.LogUtil;

import java.util.List;

/**
 * Created by Nick on 2017/2/6
 */
public class HomeAdapter extends BaseAdapter<HomeAdapter.MyViewHolder> {

    public HomeAdapter(List<GankData> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_list_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GankData gankData = (GankData) getData(position);

        holder.tv_title.setText(gankData.getDesc());

        LogUtil.d(gankData.getUrl());

        Uri uri = Uri.parse(gankData.getUrl());
        holder.sdv_pic.setImageURI(uri);
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        SimpleDraweeView sdv_pic;

        public MyViewHolder(View view) {
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            sdv_pic = (SimpleDraweeView) view.findViewById(R.id.sdv_pic);
        }
    }


}
