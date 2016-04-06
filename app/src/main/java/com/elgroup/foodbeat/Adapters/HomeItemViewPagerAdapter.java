package com.elgroup.foodbeat.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.elgroup.foodbeat.CustomDialog.NewtonCradleLoading;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.ImageLoading;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class HomeItemViewPagerAdapter extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> images;
    ImageLoading imageLoading;
    FragmentManager fragmentManager;

    public HomeItemViewPagerAdapter(Context context, ArrayList<String> eventsModels) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.images = eventsModels;
        imageLoading = new ImageLoading(mContext);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.home_item_view_pager_adapter, container, false);

        ImageView itemImg = (ImageView) itemView.findViewById(R.id.itemImg);
        NewtonCradleLoading cradleView = (NewtonCradleLoading) itemView.findViewById(R.id.cradleView);
        imageLoading.LoadImage(images.get(position), itemImg, cradleView);
        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}

