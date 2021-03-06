package com.yiguohan.idouban.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.bean.music.Musics;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ScreenUtils;
import com.yiguohan.idouban.viewImpl.music.MusicDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class MusicAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<Musics> list;
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOTER = -2;

    public MusicAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
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
            View viewFotter = View.inflate(parent.getContext(), R.layout.activity_view_footer, null);
            return new FooterViewHolder(viewFotter);
        } else {
            View view = View.inflate(parent.getContext(), R.layout.item_music, null);
            return new MusicViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            footerViewHolder.bindItem();
        } else if (holder instanceof MusicViewHolder) {
            MusicViewHolder musicViewHolder = (MusicViewHolder) holder;
            if (list.size() > 0) {
                musicViewHolder.bindItem(list.get(position), position);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
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
                    System.out.println("LOAD_NONE----");
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

    class MusicViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_music)
        ImageView ivMusic;
        @BindView(R.id.tv_music_grade)
        TextView tvMusicGrade;
        @BindView(R.id.tv_music_name)
        TextView tvMusicName;
        @BindView(R.id.tv_music_art)
        TextView tvMusicArt;
        @BindView(R.id.ll_item_view)
        LinearLayout llItemView;

        public MusicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(Musics musics, int position) {
            DisplayImgUtils.getInstance().display(context, musics.getImage(), ivMusic);
            if (!TextUtils.isEmpty(musics.getTitle())) {
                tvMusicName.setText(musics.getTitle());
            }
            if (musics.getAuthor() != null) {
                tvMusicArt.setText(musics.getAuthor().get(0).getName());
            }
            if (!TextUtils.isEmpty(musics.getRating().getAverage())) {
                tvMusicGrade.setText("评分：" + musics.getRating().getAverage());
            }
            llItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MusicDetailActivity.class);
                    intent.putExtra("id", musics.getId());
                    context.startActivity(intent);
                }
            });
        }
    }

    public void updateLoadStatus(int status) {
        this.status = status;
        notifyDataSetChanged();
    }

    public List<Musics> getList() {
        return list;
    }

    public void setList(List<Musics> list) {
        this.list = list;
    }
}
