package com.elgroup.foodbeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

import com.elgroup.foodbeat.BasketFragments.BasketParentFragment;
import com.elgroup.foodbeat.HomeFragments.HomeParentFragment;
import com.elgroup.foodbeat.ProfileFragments.ProfileParentFragment;
import com.navdrawer.SimpleSideDrawer;

/**
 * Created by Lenovo on 21-03-2016.
 */
public class HomeActivity extends BaseActivity {

    private static FragmentManager fragmentManager;
    private Toolbar toolbar;
    static SimpleSideDrawer slide_me;
    View view;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        InitViews();

    }

    private void InitViews() {

       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        slide_me = new SimpleSideDrawer(this);
        view = slide_me.setLeftBehindContentView(R.layout.left_menu);
        fragmentManager = getSupportFragmentManager();
        updateFragment(new HomeParentFragment());

    }

    public static void updateFragment(Fragment fragment) {
        String name = fragment.getClass().getName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.replace(R.id.mainFrameContainer, fragment);
            if (!(fragment instanceof HomeParentFragment))
                fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }

    }

    public void ChangeTab(View view) {
        switch (view.getId()) {
            case R.id.homeTab:
                updateFragment(new HomeParentFragment());
                break;
            case R.id.searchTab:
                break;
            case R.id.myBoardsTab:
                break;
            case R.id.profileTab:
                updateFragment(new ProfileParentFragment());
                break;
            case R.id.basketTab:
                updateFragment(new BasketParentFragment());
                break;
        }
    }

    public void ShowLocationsOnMap(View view) {
        GotoNextActivity(this, VendorsLocationMap.class);

    }

    public void showMenu(View v)
    {
        slide_me.toggleLeftDrawer();
    }
}
