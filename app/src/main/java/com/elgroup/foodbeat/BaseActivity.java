package com.elgroup.foodbeat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.elgroup.foodbeat.Utils.CommonFunctions;
import com.elgroup.foodbeat.Utils.ConnectionDetector;

/**
 * Created by Lenovo on 18-03-2016.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public ConnectionDetector cdr;
    public SharedPreferences sharedPreferences;
    public CommonFunctions commonFunctions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       /* getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT)
            setTheme(R.style.AppTheme_NoActionBar);*/
        super.onCreate(savedInstanceState);
        cdr = new ConnectionDetector(this);
        sharedPreferences = MyApplication.preference;
        commonFunctions = new CommonFunctions(this);
    }

    public boolean isInternetAvailable() {
        return cdr.isConnectingToInternet();
    }

    public void GotoNextActivity(Activity currentActivity, Class nextActivity) {
        Intent intent = new Intent(currentActivity, nextActivity);
        startActivity(intent);
    }
    public void GotoNextActivityClearTop(Activity currentActivity, Class nextActivity) {
        Intent intent = new Intent(currentActivity, nextActivity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
