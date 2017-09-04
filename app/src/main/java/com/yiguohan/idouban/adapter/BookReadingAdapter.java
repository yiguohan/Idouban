package com.yiguohan.idouban.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.bean.book.Books;
import com.yiguohan.idouban.utils.DisplayImgUtils;
import com.yiguohan.idouban.utils.ScreenUtils;
import com.yiguohan.idouban.viewImpl.book.BookDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yiguohan.
 */

public class BookReadingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private int status = 1;
    public static final int LOAD_MORE = 0;
    public static final int LOAD_PULL_TO = 1;
    public static final int LOAD_NONE = 2;
    public static final int LOAD_END = 3;
    private static final int TYPE_TOP = -1;
    private static final int TYPE_FOOT = -2;
    private List<Books> list;

    public BookReadingAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return position;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOT) {
            View view = View.inflate(parent.getContext(), R.layout.activity_view_footer, null);
            return new FooterViewHolder(view);
        } else {
            View view = View.inflate(parent.getContext(), R.layout.item_book_reading, null);
            return new BookReadingViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).bindItem();
        } else if (holder instanceof BookReadingViewHolder) {
            ((BookReadingViewHolder) holder).bindItem(list.get(position), position);
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
                    tvLoadPrompt.setText("正在加载");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_PULL_TO:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("上拉加载更多");
                    itemView.setVisibility(View.VISIBLE);
                    break;
                case LOAD_NONE:
                    progressBar.setVisibility(View.GONE);
                    tvLoadPrompt.setText("已无更多可加载");
                    break;
                case LOAD_END:
                    progressBar.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }

    }

    public void updateLoadStaues(int status) {
        this.status = status;
        notifyDataSetChanged();
    }

    class BookReadingViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_film)
        ImageView ivFilm;
        @BindView(R.id.tv_film_grade)
        TextView tvFilmeGrade;
        @BindView(R.id.tv_film_name)
        TextView tvFilmName;
        @BindView(R.id.ll_book)
        LinearLayout llBook;

        public BookReadingViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bindItem(Books books, int position) {
            ViewGroup.LayoutParams params = ivFilm.getLayoutParams();
            int width = ScreenUtils.getScreenWidthDp(context);
            int ivWidth = (width - ScreenUtils.dipToPx(context, 80)) / 3;
            params.width = ivWidth;
            double height = (420.0 / 300.0) * ivWidth;
            params.height = (int) height;
            ivFilm.setLayoutParams(params);
            if (!TextUtils.isEmpty(books.getImages().getLarge())) {
                //TODO diplay第二个参数
                DisplayImgUtils.getInstance().display(context, books.getImages().getLarge(), ivFilm);
            }
            if (!TextUtils.isEmpty(books.getRating().getAverage())) {
                tvFilmeGrade.setText("评分：" + books.getRating().getAverage());
            } else {
                tvFilmeGrade.setText("暂无评分");
            }
            if (!TextUtils.isEmpty(books.getTitle())) {
                tvFilmeGrade.setText(books.getTitle());
            }
            llBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BookDetailActivity.class);
                    intent.putExtra("id", books.getId());
                    context.startActivity(intent);
                }
            });


        }
    }

    @Override
    public int getItemCount() {
        return list.size() + 1;
    }

    public List<Books> getList() {
        return list;
    }

    public void setList(List<Books> list) {
        this.list = list;
    }
}
