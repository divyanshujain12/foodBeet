package com.elgroup.foodbeat.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.elgroup.foodbeat.Models.HomeFragmentModel;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.CustomViews.CustomViewPager;
import com.neopixl.pixlui.components.textview.TextView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class HomeFragmentAdapter extends RecyclerView
        .Adapter<HomeFragmentAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<HomeFragmentModel> mDataset;
    private Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtPrice;
        CustomViewPager itemImageViewPager;
        CirclePageIndicator circleIndicator;

        public DataObjectHolder(View itemView, int viewType) {
            super(itemView);
            itemImageViewPager = (CustomViewPager) itemView.findViewById(R.id.itemImageViewPager);
            circleIndicator = (CirclePageIndicator) itemView.findViewById(R.id.circleIndicator);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtPrice = (TextView) itemView.findViewById(R.id.txtPrice);

            Log.i(LOG_TAG, "Adding Listener");

        }


    }

    public HomeFragmentAdapter(Context context, ArrayList<HomeFragmentModel> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_fragment_adapter, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, viewType);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        HomeFragmentModel homeFragmentModel = mDataset.get(position);
        holder.circleIndicator.setOrientation(LinearLayout.HORIZONTAL);
        holder.itemImageViewPager.setAdapter(new HomeItemViewPagerAdapter(context, homeFragmentModel.getImages()));
        holder.circleIndicator.setViewPager(holder.itemImageViewPager);
        holder.txtName.setText(homeFragmentModel.getName());
        holder.txtPrice.setText(homeFragmentModel.getPrice());

    }

    public void addItem(HomeFragmentModel dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}