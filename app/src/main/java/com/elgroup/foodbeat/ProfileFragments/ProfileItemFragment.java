package com.elgroup.foodbeat.ProfileFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elgroup.foodbeat.Adapters.ProfileItemAdapter;
import com.elgroup.foodbeat.Models.ProfileItemModel;
import com.elgroup.foodbeat.R;

import java.util.ArrayList;

/**
 * Created by Lenovo on 30-03-2016.
 */
public class ProfileItemFragment extends Fragment {

    private RecyclerView itemRV;
    public ArrayList<ProfileItemModel> profileItemModels;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_items_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {
        itemRV = (RecyclerView) getView().findViewById(R.id.itemRV);
        setUpArrayList();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        itemRV.setLayoutManager(gridLayoutManager);
        itemRV.setAdapter(new ProfileItemAdapter(getActivity(), profileItemModels));

    }

    private void setUpArrayList() {
        profileItemModels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ProfileItemModel profileItemModel = new ProfileItemModel();
            profileItemModel.setProduct_image(getString(R.string.pic6));
            profileItemModel.setProduct_old_price("$25");
            profileItemModel.setProduct_price("$20");
            profileItemModel.setProduct_name("Strawberry");
            profileItemModels.add(profileItemModel);
        }
    }
}
