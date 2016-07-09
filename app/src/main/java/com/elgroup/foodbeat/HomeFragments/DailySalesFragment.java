package com.elgroup.foodbeat.HomeFragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.elgroup.foodbeat.Adapters.DailySalesFragmentAdapter;
import com.elgroup.foodbeat.Models.SaleFragmentModel;
import com.elgroup.foodbeat.MyApplication;
import com.elgroup.foodbeat.ProductDetailActivity;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.CallBackInterface;
import com.elgroup.foodbeat.Utils.CallWebService;
import com.elgroup.foodbeat.Utils.Constants;
import com.elgroup.foodbeat.Utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Lenovo on 22-03-2016.
 */
public class DailySalesFragment extends Fragment implements CallBackInterface{


    private RecyclerView saleContentRV;
    public static ArrayList<SaleFragmentModel> saleFragmentModels;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.daily_sales_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {
        saleFragmentModels = new ArrayList<>();
        saleContentRV = (RecyclerView) getView().findViewById(R.id.saleContentRV);
        saleContentRV.setLayoutManager(new LinearLayoutManager(getActivity()));

        SharedPreferences pref = MyApplication.preference;
        String accesstoken = pref.getString("accesstoken","");
        String usertype = pref.getString("usertype","");

        System.out.println("Accesstoken" + accesstoken +" type " +usertype);

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("accesstoken", accesstoken);
        params.put("usertype",usertype);

        String url = Constants.WebServices.USER_HOME_SALES;
        CallWebService.getInstance(getActivity(), true).hitJSONObjectVolleyWebService1(Request.Method.POST, url, params, this);

        saleContentRV.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                startActivity(intent);
            }
        }));

    }

    @Override
    public void onJsonObjectSuccess(JSONObject object) {

        Log.i("sale res", object.toString());

        try {
            JSONArray products = object.getJSONArray("products");
            for(int i =0; i<products.length();i++){

                JSONObject item = products.getJSONObject(i);
                SaleFragmentModel homeFragmentModel = new SaleFragmentModel();
                homeFragmentModel.setId(item.getString("id"));
                homeFragmentModel.setName(item.getString("productname"));
                homeFragmentModel.setPrice(item.getString("price"));
                JSONArray images = item.getJSONArray("images");
                ArrayList<String> arrayList = new ArrayList<>();
                String path = "http://foodbeat.buyingoffice.in/upload/products/";
                for (int j =0; j<images.length();j++)
                {
                    JSONObject image = images.getJSONObject(j);

                    arrayList.add(path+image.getString("imagename"));
                }


                homeFragmentModel.setImages(arrayList);
                saleFragmentModels.add(homeFragmentModel);

            }

            DailySalesFragmentAdapter salesFragmentAdapter = new DailySalesFragmentAdapter(getActivity(), saleFragmentModels);
            saleContentRV.setAdapter(salesFragmentAdapter);
        }
        catch (Exception e)
        {

        }

    }

    @Override
    public void onJsonArrarSuccess(JSONArray array) {

    }

    @Override
    public void onFailure(String str) {

    }
}
