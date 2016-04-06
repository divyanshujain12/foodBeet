package com.elgroup.foodbeat.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elgroup.foodbeat.CustomDialog.NewtonCradleLoading;
import com.elgroup.foodbeat.Models.ProfileItemModel;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.ImageLoading;
import com.elgroup.foodbeat.Utils.RoundedImageView;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 30-03-2016.
 */
public class ProfileItemAdapter extends RecyclerView
        .Adapter<ProfileItemAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<ProfileItemModel> mDataset;
    private Context context;
    private ImageLoading imageLoading;

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

    public ProfileItemAdapter(Context context, ArrayList<ProfileItemModel> myDataset) {
        this.context = context;
        mDataset = myDataset;
        imageLoading = new ImageLoading(context);
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

        ProfileItemModel profileItemModel = mDataset.get(position);
        imageLoading.LoadImage(profileItemModel.getProduct_image(), holder.productImage, holder.loadingView);
        holder.txtProductName.setText(profileItemModel.getProduct_name());
        holder.txtProductNewPrice.setText(profileItemModel.getProduct_price());
        holder.txtProductOldPrice.setText(profileItemModel.getProduct_old_price());
    }

    public void addItem(ProfileItemModel dataObj, int index) {
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