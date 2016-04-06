package com.elgroup.foodbeat;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.elgroup.foodbeat.Adapters.HomeItemViewPagerAdapter;
import com.elgroup.foodbeat.HomeFragments.HomeFragment;
import com.elgroup.foodbeat.CustomViews.CustomViewPager;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class ProductDetailActivity extends BaseActivity {

    private CollapsingToolbarLayout collapsing_toolbar;
    private Toolbar toolbar;
    private AppBarLayout app_bar_layout;
    private CustomViewPager itemImageViewPager;
    private CirclePageIndicator circleIndicator;

    //TextView txtNameToolbar = null;

    @SuppressWarnings("deprecation")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
            setTheme(R.style.AppTheme_NoActionBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail_activity);

        InitViews();

    }

    private void InitViews() {
        ConfigureToolbar("STRAWBERRY", "$15");
        itemImageViewPager = (CustomViewPager) findViewById(R.id.itemImageViewPager);
        circleIndicator = (CirclePageIndicator) findViewById(R.id.circleIndicator);
        circleIndicator.setOrientation(LinearLayout.HORIZONTAL);
        itemImageViewPager.setAdapter(new HomeItemViewPagerAdapter(this, HomeFragment.homeFragmentModels.get(0).getImages()));
        circleIndicator.setViewPager(itemImageViewPager);
    }


    private void ConfigureToolbar(String text, String price) {
        app_bar_layout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsing_toolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        collapsing_toolbar.setTitle(text + "        " + price);
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
}
