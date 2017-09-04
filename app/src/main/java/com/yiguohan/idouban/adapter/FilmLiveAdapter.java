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
import com.yiguohan.idouban.bean.top250.Subjects;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ScreenUtils;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class FilmLiveAdapter extends EasyRecyclerViewAdapter<Subjects> {

    Context context;

    public FilmLiveAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_film_live, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position, Subjects data) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tvName.setText(data.getTitle());
        ViewGroup.LayoutParams params = viewHolder.ivFilm.getLayoutParams();
        int width = ScreenUtils.getScreenWidthDp(context);
        int ivWidth = (width - ScreenUtils.dipToPx(context, 80)) / 3;
        params.width = ivWidth;
        double height = (420.0 / 300 / 0) * ivWidth;
        params.height = (int) height;
        viewHolder.ivFilm.setLayoutParams(params);
        if (data.getImages() != null && !TextUtils.isEmpty(data.getImages().getLarge())) {
            DisplayImgUtils.getInstance().display(context, data.getImages().getLarge(), viewHolder.ivFilm);
        }
        if (!TextUtils.isEmpty("" + data.getRating().getAverage())) {
            viewHolder.tvGrade.setText("评分：" + data.getRating().getAverage());
        } else {
            viewHolder.tvGrade.setText("暂无评分");
        }


    }

    class ViewHolder extends EasyViewHolder {

        @BindView(R.id.iv_film)
        ImageView ivFilm;
        @BindView(R.id.tv_film_grade)
        TextView tvGrade;
        @BindView(R.id.tv_film_name)
        TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
