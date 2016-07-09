package com.elgroup.foodbeat.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.elgroup.foodbeat.CustomViews.CustomViewPager;
import com.elgroup.foodbeat.Models.SaleFragmentModel;
import com.elgroup.foodbeat.R;
import com.neopixl.pixlui.components.textview.TextView;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class DailySalesFragmentAdapter extends RecyclerView
        .Adapter<DailySalesFragmentAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<SaleFragmentModel> mDataset;
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

    public DailySalesFragmentAdapter(Context context, ArrayList<SaleFragmentModel> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sale_fragment_adapter, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, viewType);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        SaleFragmentModel saleFragmentModel = mDataset.get(position);
        holder.circleIndicator.setOrientation(LinearLayout.HORIZONTAL);
         holder.itemImageViewPager.setAdapter(new SaleItemViewPagerAdapter(context, saleFragmentModel.getImages()));
         holder.circleIndicator.setViewPager(holder.itemImageViewPager);
        holder.txtName.setText(saleFragmentModel.getName());
        holder.txtPrice.setText(saleFragmentModel.getPrice());

    }

    public void addItem(SaleFragmentModel dataObj, int index) {
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