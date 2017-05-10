package com.sir.app.retrofit.view.cartoon.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.cardstack.CardStackView;
import com.sir.app.cardstack.StackAdapter;
import com.sir.app.retrofit.R;
import com.sir.app.retrofit.model.cartoon.bean.Chapter;


/**
 * Created by zhuyinan on 2017/5/4.
 */

public class ChapterAdapter extends StackAdapter<Chapter> {

    Integer[] ColorBG;
    String explain;

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public ChapterAdapter(Context context) {
        super(context);
        ColorBG = new Integer[]{
                R.color.md_red_500,
                R.color.md_pink_500,
                R.color.md_purple_500,
                R.color.md_deep_purple_500,
                R.color.md_indigo_500,
                R.color.md_blue_500,
                R.color.md_light_blue_500,
                R.color.md_cyan_500,
                R.color.md_teal_500,
                R.color.md_green_500,
                R.color.md_light_green_500,
                R.color.md_lime_500,
                R.color.md_yellow_500,
                R.color.md_amber_500,
                R.color.md_orange_500,
                R.color.md_deep_orange_500,
                R.color.md_brown_500,
                R.color.md_grey_500,
                R.color.md_blue_grey_500
        };
    }


    @Override
    public void bindView(final Chapter data, int position, CardStackView.ViewHolder holder) {
        ChapterViewHolder chapterViewHolder = (ChapterViewHolder) holder;
        chapterViewHolder.onBind(data, ColorBG[(int) (Math.random() * ColorBG.length)], explain, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (itemClickListener != null) {
                    itemClickListener.OnItemClickListener(data);
                }
            }
        });
    }

    private ItemClickListener itemClickListener;

    public void setOnItemClickListener(ItemClickListener listener) {
        this.itemClickListener = listener;
    }

    public interface ItemClickListener {
        void OnItemClickListener(Chapter data);
    }


    @Override
    protected CardStackView.ViewHolder onCreateView(ViewGroup parent, int viewType) {
        View view = getLayoutInflater().inflate(R.layout.item_chapter, parent, false);
        return new ChapterViewHolder(view);
    }

    static class ChapterViewHolder extends CardStackView.ViewHolder {

        TextView chapterTitle;
        TextView chapterRefreshTime;
        TextView chapterExplain;
        ImageView chapterCover;
        RelativeLayout frameChapter;
        Button chapterRead;

        public ChapterViewHolder(View view) {
            super(view);
            frameChapter = (RelativeLayout) view.findViewById(R.id.frame_chapter);
            chapterTitle = (TextView) view.findViewById(R.id.chapter_title);
            chapterRefreshTime = (TextView) view.findViewById(R.id.chapter_refresh_time);
            chapterCover = (ImageView) view.findViewById(R.id.chapter_cover);
            chapterRead = (Button) view.findViewById(R.id.chapter_read);
            chapterExplain = (TextView) view.findViewById(R.id.chapter_explain);
        }

        public void onBind(Chapter data, int resId, String explain, View.OnClickListener listener) {
            frameChapter.setBackgroundResource(resId);
            chapterTitle.setText(data.getTitle());
            chapterRefreshTime.setText(data.getRefreshTimeStr());
            chapterExplain.setText(explain);
            ImageLoader.getInstance().displayImage(data.getFrontCover(), chapterCover);
            chapterRead.setOnClickListener(listener);
        }


        @Override
        public void onItemExpand(boolean b) {

        }

    }


}
