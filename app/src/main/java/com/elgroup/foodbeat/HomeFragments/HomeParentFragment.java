package com.elgroup.foodbeat.HomeFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elgroup.foodbeat.Adapters.ViewPagerAdapter;
import com.elgroup.foodbeat.R;

/**
 * Created by Lenovo on 22-03-2016.
 */
public class HomeParentFragment extends Fragment {

    private TabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_parent_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {
        tabs = (TabLayout) getView().findViewById(R.id.tabs);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);

        ConfigViewPager();

    }

    private void ConfigViewPager() {

        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new HomeFragment(), getString(R.string.home));
        viewPagerAdapter.addFragment(new DailySalesFragment(), getString(R.string.daily_sales));
        viewPagerAdapter.addFragment(new RecentFragment(), getString(R.string.recent));
        viewPager.setAdapter(viewPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabTextColors(getResources().getColor(R.color.white_with_alpha), getResources().getColor(android.R.color.white));

    }



}
