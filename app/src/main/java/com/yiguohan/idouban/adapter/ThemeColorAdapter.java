package com.yiguohan.idouban.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yiguohan.idouban.R;
import com.yiguohan.idouban.base.EasyRecyclerViewAdapter;
import com.yiguohan.idouban.bean.home.ThemeColor;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yiguohan.
 */

public class ThemeColorAdapter extends EasyRecyclerViewAdapter<ThemeColor> {

    private int mPosition;

    public ThemeColorAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_theme_color,parent,false);
        return new ThemeColorViewHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder holder, int position, ThemeColor data) {
        ((ThemeColorViewHolder)holder).theme_color.setImageResource(data.getColor());
        if (data.isChosen()){
            ((ThemeColorViewHolder)holder).chosen.setVisibility(View.VISIBLE);
            mPosition = position;
        }else {
            ((ThemeColorViewHolder)holder).chosen.setVisibility(View.GONE);
        }
    }

    class ThemeColorViewHolder extends EasyViewHolder{

        @BindView(R.id.theme_color)
        CircleImageView theme_color;
        @BindView(R.id.choose)
        ImageView chosen;

        public ThemeColorViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public int getPosition(){
        return mPosition;
    }
}
