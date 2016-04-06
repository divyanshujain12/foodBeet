package com.elgroup.foodbeat.HomeFragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elgroup.foodbeat.Adapters.HomeFragmentAdapter;
import com.elgroup.foodbeat.Models.HomeFragmentModel;
import com.elgroup.foodbeat.ProductDetailActivity;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.RecyclerItemClickListener;

import java.util.ArrayList;

/**
 * Created by Lenovo on 22-03-2016.
 */
public class HomeFragment extends Fragment {
    private RecyclerView homeContentRV;
    public static ArrayList<HomeFragmentModel> homeFragmentModels;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {
        homeFragmentModels = new ArrayList<>();
        homeContentRV = (RecyclerView) getView().findViewById(R.id.homeContentRV);
        homeContentRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        for (int i = 0; i < 10; i++) {
            HomeFragmentModel homeFragmentModel = new HomeFragmentModel();
            homeFragmentModel.setId(String.valueOf(i));
            homeFragmentModel.setName("BANANA");
            homeFragmentModel.setPrice("200 rs");
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add(getString(R.string.pic1));
            arrayList.add(getString(R.string.pic2));
            arrayList.add(getString(R.string.pic3));
            arrayList.add(getString(R.string.pic4));
            arrayList.add(getString(R.string.pic5));
            arrayList.add(getString(R.string.pic6));

            homeFragmentModel.setImages(arrayList);
            homeFragmentModels.add(homeFragmentModel);
        }
        HomeFragmentAdapter homeFragmentAdapter = new HomeFragmentAdapter(getActivity(), homeFragmentModels);
        homeContentRV.setAdapter(homeFragmentAdapter);

        homeContentRV.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
            }
        }));
    }


}
