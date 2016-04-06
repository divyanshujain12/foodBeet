package com.elgroup.foodbeat.ProfileFragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elgroup.foodbeat.Adapters.ViewPagerAdapter;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.CustomViews.CustomTabLayout;
import com.elgroup.foodbeat.Utils.RoundedImageView;
import com.neopixl.pixlui.components.textview.TextView;

/**
 * Created by Lenovo on 30-03-2016.
 */
public class ProfileParentFragment extends Fragment {

    private RoundedImageView profileImage;
    private TextView txtName, txtFarmName, txtAddress;
    private CustomTabLayout tabs;
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_parent_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {
        profileImage = (RoundedImageView) getView().findViewById(R.id.profileImage);
        txtName = (TextView) getView().findViewById(R.id.txtName);
        txtFarmName = (TextView) getView().findViewById(R.id.txtFarmName);
        txtAddress = (TextView) getView().findViewById(R.id.txtAddress);

        tabs = (CustomTabLayout) getView().findViewById(R.id.tabs);
        viewPager = (ViewPager) getView().findViewById(R.id.viewPager);
        ConfigViewPager();
    }

    private void ConfigViewPager() {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ProfileItemFragment(), getString(R.string.item));
        viewPagerAdapter.addFragment(new ProfileFeedbackFragment(), getString(R.string.feedback));
        viewPagerAdapter.addFragment(new ProfilePicturesFragment(), getString(R.string.pictures));
        viewPagerAdapter.addFragment(new ProfileAboutFragment(), getString(R.string.about));
        viewPager.setAdapter(viewPagerAdapter);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabTextColors(getResources().getColor(R.color.white_with_alpha), getResources().getColor(android.R.color.white));

    }
}
