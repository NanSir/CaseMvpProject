package com.sir.app.retrofit.view.widget;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.sir.app.retrofit.R;


import java.util.ArrayList;
import java.util.List;

/**
 * 图片轮播
 */
public class CarouselImages extends FrameLayout {
    private int count;
    private ImageLoader mImageLoader;
    private List<ImageView> imageViews;
    private ViewPager bannerViewPager;
    private boolean isAutoPlay;
    private int currentItem;

    private LinearLayout ll_dot;
    private List<ImageView> iv_dots;
    private Handler handler = new Handler();
    private DisplayImageOptions options;

    public CarouselImages(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initImageLoader();
        initData();
    }

    public CarouselImages(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselImages(Context context) {
        this(context, null);
    }

    private void initData() {
        imageViews = new ArrayList<>();
        iv_dots = new ArrayList<>();

    }

    public void setImagesUrl(Context context,String[] imagesUrl) {
        initLayout(context);
        initImgFromNet(context,imagesUrl);
        showTime();
    }

    public void setImagesRes(Context context,int[] imagesRes) {
        initLayout(context);
        initImgFromRes(context,imagesRes);
        showTime();
    }

    private void initLayout(Context context) {
        imageViews.clear();
        View view = LayoutInflater.from(context).inflate(
                R.layout.item_carousel_barnner, this, true);
        bannerViewPager = (ViewPager) view.findViewById(R.id.banner_viewPager);
        ll_dot = (LinearLayout) view.findViewById(R.id.banner_liner);
        ll_dot.removeAllViews();

    }

    private void initImgFromRes(Context context,int[] imagesRes) {
        count = imagesRes.length;
        for (int i = 0; i < count; i++) {
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            iv_dot.setImageResource(R.mipmap.ic_banner_dot_blur);
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        iv_dots.get(0).setImageResource(R.mipmap.ic_banner_focus);

        for (int i = 0; i <= count + 1; i++) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.FIT_XY);
            if (i == 0) {
                iv.setImageResource(imagesRes[count - 1]);
            } else if (i == count + 1) {
                iv.setImageResource(imagesRes[0]);
            } else {
                iv.setImageResource(imagesRes[i - 1]);
            }
            imageViews.add(iv);
        }
    }

    private void initImgFromNet(Context context,String[] imagesUrl) {
        count = imagesUrl.length;
        for (int i = 0; i < count; i++) {
            ImageView iv_dot = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 5;
            params.rightMargin = 5;
            iv_dot.setImageResource(R.mipmap.ic_banner_dot_blur);
            ll_dot.addView(iv_dot, params);
            iv_dots.add(iv_dot);
        }
        iv_dots.get(0).setImageResource(R.mipmap.ic_banner_focus);

        for (int i = 0; i <= count + 1; i++) {
            ImageView iv = new ImageView(context);
            iv.setScaleType(ScaleType.FIT_XY);
            iv.setBackgroundResource(R.mipmap.ic_fap);
            if (i == 0) {
                mImageLoader.displayImage(imagesUrl[count - 1], iv, options);
            } else if (i == count + 1) {
                mImageLoader.displayImage(imagesUrl[0], iv, options);
            } else {
                mImageLoader.displayImage(imagesUrl[i - 1], iv, options);
            }
            imageViews.add(iv);
        }
    }

    private void showTime() {
        bannerViewPager.setAdapter(new MyPagerAdapter());
        bannerViewPager.setFocusable(true);
        bannerViewPager.setCurrentItem(1);
        currentItem = 1;
        bannerViewPager.addOnPageChangeListener(new MyOnPageChangeListener());
        startPlay();
    }

    private void startPlay() {
        isAutoPlay = true;
        handler.postDelayed(task, 3000);
    }

    public void initImageLoader() {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        mImageLoader = ImageLoader.getInstance();
    }

    private final Runnable task = new Runnable() {

        @Override
        public void run() {
            if (isAutoPlay) {
                currentItem = currentItem % (count + 1) + 1;
                if (currentItem == 1) {
                    bannerViewPager.setCurrentItem(currentItem, false);
                    handler.post(task);
                } else {
                    bannerViewPager.setCurrentItem(currentItem);
                    handler.postDelayed(task, 5000);
                }
            } else {
                handler.postDelayed(task, 5000);
            }
        }
    };

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            container.addView(imageViews.get(position));
            View viewClick = imageViews.get(position);
            viewClick.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        listener.OnItemBanner(position);
                    }
                }
            });
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

    }

    class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            switch (arg0) {
                case 1:
                    isAutoPlay = false;
                    break;
                case 2:
                    isAutoPlay = true;
                    break;
                case 0:
                    if (bannerViewPager.getCurrentItem() == 0) {
                        bannerViewPager.setCurrentItem(count, false);
                    } else if (bannerViewPager.getCurrentItem() == count + 1) {
                        bannerViewPager.setCurrentItem(1, false);
                    }
                    currentItem = bannerViewPager.getCurrentItem();
                    isAutoPlay = true;
                    break;
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            for (int i = 0; i < iv_dots.size(); i++) {
                if (i == arg0 - 1) {
                    iv_dots.get(i).setImageResource(R.mipmap.ic_banner_focus);
                } else {
                    iv_dots.get(i).setImageResource(R.mipmap.ic_banner_dot_blur);
                }
            }
        }
    }

    private OnItemBannerListener listener;

    public void setOnItemBannerListener(OnItemBannerListener bannerListener) {
        this.listener = bannerListener;
    }

    public interface OnItemBannerListener {
        void OnItemBanner(int index);
    }
}
