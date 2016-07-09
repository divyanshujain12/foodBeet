package com.elgroup.foodbeat.HomeFragments;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.elgroup.foodbeat.Adapters.RecentItemAdapter;
import com.elgroup.foodbeat.Models.RecentItemModel;
import com.elgroup.foodbeat.MyApplication;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.CallBackInterface;
import com.elgroup.foodbeat.Utils.CallWebService;
import com.elgroup.foodbeat.Utils.Constants;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenovo on 22-03-2016.
 */
public class RecentFragment extends Fragment implements CallBackInterface {

    private RecyclerView itemRV;
    public ArrayList<RecentItemModel> recentItemModels;
    RecentItemAdapter recentFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recent_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        SharedPreferences pref = MyApplication.preference;
        String accesstoken = pref.getString("accesstoken","");
        String usertype = pref.getString("usertype","");

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("accesstoken", accesstoken);
        params.put("usertype", usertype);

        String url = Constants.WebServices.USER_HOME_RECENT;
        CallWebService.getInstance(getActivity(), true).hitJSONObjectVolleyWebService1(Request.Method.POST, url, params, this);
        //itemRV.setAdapter(new RecentItemAdapter(getActivity(), recentItemModels));

        InitViews();
    }

    private void InitViews() {

        recentItemModels = new ArrayList<>();
        itemRV = (RecyclerView) getView().findViewById(R.id.itemRV);


    }

    @Override
    public void onJsonObjectSuccess(JSONObject object) {

        Log.i("home recent res", object.toString());


        try {
            JSONArray products = object.getJSONArray("products");
            for (int i = 0; i < products.length(); i++) {

                JSONObject item = products.getJSONObject(i);
                RecentItemModel recentFragmentModel = new RecentItemModel();
                recentFragmentModel.setProduct_image(getString(R.string.pic6));
                recentFragmentModel.setProduct_old_price(item.getString("price"));
                recentFragmentModel.setProduct_price(item.getString("price"));
                recentFragmentModel.setProduct_name(item.getString("productname"));
                JSONObject images = item.getJSONObject("images");
                ArrayList<String> arrayList = new ArrayList<>();
                String path = "http://foodbeat.buyingoffice.in/upload/products/";

                recentFragmentModel.setProduct_image(path+images.getString("imagename"));
                recentItemModels.add(recentFragmentModel);


            }

            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
            itemRV.setLayoutManager(gridLayoutManager);
            recentFragmentAdapter =  new RecentItemAdapter(getActivity(), recentItemModels);
            itemRV.setAdapter(recentFragmentAdapter);

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onJsonArrarSuccess(JSONArray array) {

    }

    @Override
    public void onFailure(String str) {

    }
}
