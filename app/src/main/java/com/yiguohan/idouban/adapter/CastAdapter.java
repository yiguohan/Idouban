package com.yiguohan.idouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.filmdetail.FilmPeople;
import com.yiguohan.idouban.utils.DisplayImgUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class CastAdapter extends EasyRecyclerViewAdapter<FilmPeople> {

    private Context context;

    public CastAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_people, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position, FilmPeople data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        DisplayImgUtils.getInstance().display(context, data.getAvatars().getLarge(), viewHolder.ivAvatar);
        viewHolder.tvNameChinese.setText(data.getName());
        if (data.getType() == 1) {
            viewHolder.tvNameEnglish.setText(" [导演] ");
        } else {
            viewHolder.tvNameEnglish.setText(" [演员] ");
        }
    }

    class ViewHolder extends EasyViewHolder {
        @BindView(R.id.iv_avatar)
        ImageView ivAvatar;
        @BindView(R.id.tv_name_chinese)
        TextView tvNameChinese;
        @BindView(R.id.tv_name_english)
        TextView tvNameEnglish;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
