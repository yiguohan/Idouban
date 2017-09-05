package com.yiguohan.idouban.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.filmusbox.Subjects;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ScreenUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class FilmUsBoxAdapter extends EasyRecyclerViewAdapter<Subjects> {

    Context context;

    public FilmUsBoxAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film_live, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position, Subjects data) {
        ViewHolder viewHolder = (ViewHolder) holder;

        viewHolder.tvFilmName.setText(data.getSubject().getTitle());
        ViewGroup.LayoutParams params = holder.itemView.getLayoutParams();
        int width = ScreenUtils.getScreenWidthDp(context);
        int ivWidth = (width - ScreenUtils.dipToPx(context, 80)) / 3;
        params.width = ivWidth;
        double height = (420.0 / 300.0) * ivWidth;
        params.height = (int) height;
        viewHolder.ivFilm.setLayoutParams(params);
        if (data.getSubject().getImages().getLarge() != null && !TextUtils.isEmpty(data.getSubject().getImages().getLarge())) {
            DisplayImgUtils.getInstance().display(context, data.getSubject().getImages().getLarge(), viewHolder.ivFilm);
        }
        if (!TextUtils.isEmpty(data.getSubject().getRating().getAverage() + "")) {
            viewHolder.tvFilmGrade.setText("评分" + data.getSubject().getRating().getAverage());
        } else {
            viewHolder.tvFilmGrade.setText("暂无评分");
        }
    }


    class ViewHolder extends EasyViewHolder {
        @BindView(R.id.iv_film)
        ImageView ivFilm;
        @BindView(R.id.tv_film_name)
        TextView tvFilmName;
        @BindView(R.id.tv_film_grade)
        TextView tvFilmGrade;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
