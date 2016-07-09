package com.elgroup.foodbeat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.elgroup.foodbeat.Adapters.HomeItemViewPagerAdapter;
import com.elgroup.foodbeat.CustomViews.CustomViewPager;
import com.elgroup.foodbeat.HomeFragments.HomeFragment;
import com.elgroup.foodbeat.Utils.CallBackInterface;
import com.elgroup.foodbeat.Utils.CallWebService;
import com.elgroup.foodbeat.Utils.Constants;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class ProductDetailActivity extends BaseActivity implements CallBackInterface {

    private CollapsingToolbarLayout collapsing_toolbar;
    private Toolbar toolbar;
    private AppBarLayout app_bar_layout;
    private CustomViewPager itemImageViewPager;
    private CirclePageIndicator circleIndicator;
    String id, position, product_name, product_price, address, city, description, sellerid, first_name, last_name, images;


    /*TextView txtNameToolbar = null;*/

    @SuppressWarnings("deprecation")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);

        InitViews();


    }

    private void InitViews() {

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        position = intent.getStringExtra("position");

        SharedPreferences pref = MyApplication.preference;
        String accesstoken = pref.getString("accesstoken","");
        String usertype = pref.getString("usertype","");

        // System.out.println("Accesstoken" + accesstoken +" type " +usertype);
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("accesstoken", accesstoken);
        params.put("usertype", usertype);
        params.put("productid", id);

        String url = Constants.WebServices.PRODUCT_DETAIL;
        CallWebService.getInstance(ProductDetailActivity.this, true).hitJSONObjectVolleyWebService1(Request.Method.POST, url, params, this);



    }


    private void ConfigToolbar(String text, String price) {

        app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsing_toolbar.setTitle(text + "           " + price);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.like) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onJsonObjectSuccess(JSONObject object) {

        Log.i("Prod detail", object.toString());
        try {
            JSONArray product = object.getJSONArray("product");
            JSONObject obj = product.getJSONObject(0);

            product_name = obj.getString("productname");
            product_price = obj.getString("price");
            description = obj.getString("descp");

        }
        catch (Exception e){

        }
        ConfigToolbar(product_name, product_price);
        itemImageViewPager = (CustomViewPager) findViewById(R.id.itemImageViewPager);
        circleIndicator = (CirclePageIndicator) findViewById(R.id.circleIndicator);
        circleIndicator.setOrientation(LinearLayout.HORIZONTAL);
        int pos = Integer.parseInt(position);
        itemImageViewPager.setAdapter(new HomeItemViewPagerAdapter(this, HomeFragment.homeFragmentModels.get(pos).getImages()));
        circleIndicator.setViewPager(itemImageViewPager);

    }

    @Override
    public void onJsonArrarSuccess(JSONArray array) {



    }

    @Override
    public void onFailure(String str) {

        

    }
}
