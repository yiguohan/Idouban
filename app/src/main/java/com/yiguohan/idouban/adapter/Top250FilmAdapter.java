package com.yiguohan.idouban.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.top250.Root;
import com.yiguohan.idouban.bean.top250.Subjects;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ScreenUtils;
import com.yiguohan.idouban.viewImpl.filmdetail.FilmDetailActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class Top250FilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private Root root;
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_HEADER = -1;
    private static final int TYPE_FOOTER = -2;

    public Top250FilmAdapter(Context context, Root root) {
        this.context = context;
        this.root = root;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        } else if (holder instanceof FilmUsBoxViewHolder) {
            FilmUsBoxViewHolder filmUsBoxViewHolder = (FilmUsBoxViewHolder) holder;
            filmUsBoxViewHolder.bindItem(root.getSubjects().get(position), position);
        }
    }

    @Override
    public int getItemCount() {
        return root.getSubjects().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER) {
            View view = View.inflate(parent.getContext(), R.layout.activity_view_footer, null);
            return new FooterViewHolder(view);

        } else {
            View rootView = View.inflate(parent.getContext(), R.layout.item_film_us, null);
            return new FilmUsBoxViewHolder(rootView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_load_prompt)
        TextView tvLoadPrompt;
        @BindView(R.id.progressbar)
        ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            LinearLayoutCompat.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ScreenUtils.dipToPx(context, 40));
            itemView.setLayoutParams(params);
        }

        private void bindItem() {
            switch (status) {
                case LOAD_MORE:
                    progressBar.setVisibility(View.VISIBLE);
                    tvLoadPrompt.setText("正在加载...");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("已无更多加载");
                    break;
                case LOAD_END:
                    itemView.setVisibility(View.GONE);
                default:
                    break;
            }
        }
    }

    class FilmUsBoxViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_film)
        ImageView ivFilm;
        @BindView(R.id.tv_film)
        TextView tvFilm;
        @BindView(R.id.tv_film_en)
        TextView tvFilmEn;
        @BindView(R.id.tv_film_grade)
        TextView tvFilmGrade;
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.ll_item_view)
        LinearLayout llItemView;


        public FilmUsBoxViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(Subjects subjects, int position) {
            DisplayImgUtils.getInstance().display(context, subjects.getImages().getLarge(), ivFilm);
            tvFilm.setText(subjects.getTitle());
            tvFilmEn.setText(subjects.getOriginal_title());
            tvFilmGrade.setText("评分：" + subjects.getRating().getAverage());
            tvRank.setText(position + 1);

            llItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FilmDetailActivity.class);
                    intent.putExtra(FilmDetailActivity.EXTRA_ID, subjects.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }
}
