package com.elgroup.foodbeat.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elgroup.foodbeat.CustomDialog.NewtonCradleLoading;
import com.elgroup.foodbeat.HomeActivity;
import com.elgroup.foodbeat.Models.RecentItemModel;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.RoundedImageView;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 30-03-2016.
 */
public class RecentItemAdapter extends RecyclerView
        .Adapter<RecentItemAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<RecentItemModel> mDataset;
    private Context context;

    public static class DataObjectHolder extends RecyclerView.ViewHolder {

        ImageView productImage;
        TextView txtProductName, txtProductNewPrice, txtProductOldPrice;
        RoundedImageView userImage;
        NewtonCradleLoading loadingView;


        public DataObjectHolder(View itemView, int viewType) {
            super(itemView);
            productImage = (ImageView) itemView.findViewById(R.id.productImage);
            loadingView = (NewtonCradleLoading) itemView.findViewById(R.id.loadingView);
            // loadingView.setBallsSize(5);
            txtProductName = (TextView) itemView.findViewById(R.id.txtProductName);
            txtProductNewPrice = (TextView) itemView.findViewById(R.id.txtProductNewPrice);
            txtProductOldPrice = (TextView) itemView.findViewById(R.id.txtProductOldPrice);
            userImage = (RoundedImageView) itemView.findViewById(R.id.userImage);
            Log.i(LOG_TAG, "Adding Listener");

        }


    }

    public RecentItemAdapter(Context context, ArrayList<RecentItemModel> myDataset) {
        this.context = context;
        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.profile_item_adapter, parent, false);


        DataObjectHolder dataObjectHolder = new DataObjectHolder(view, viewType);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {

        RecentItemModel recentItemModel = mDataset.get(position);
        HomeActivity.imageLoading.LoadImage(recentItemModel.getProduct_image(), holder.productImage, holder.loadingView);
        holder.txtProductName.setText(recentItemModel.getProduct_name());
        holder.txtProductNewPrice.setText(recentItemModel.getProduct_price());
        holder.txtProductOldPrice.setText(recentItemModel.getProduct_old_price());
    }

    public void addItem(RecentItemModel dataObj, int index) {
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