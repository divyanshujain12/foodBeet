package com.elgroup.foodbeat;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.elgroup.foodbeat.BasketFragments.BasketParentFragment;
import com.elgroup.foodbeat.HomeFragments.HomeParentFragment;
import com.elgroup.foodbeat.ProfileFragments.ProfileParentFragment;

/**
 * Created by Lenovo on 21-03-2016.
 */
public class HomeActivity extends BaseActivity {

    private static FragmentManager fragmentManager;
    private Toolbar toolbar;

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
        setContentView(R.layout.home_activity);

        InitViews();

    }

    private void InitViews() {

       /* toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
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
}
