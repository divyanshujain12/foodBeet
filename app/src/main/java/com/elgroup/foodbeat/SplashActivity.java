package com.elgroup.foodbeat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashActivity extends BaseActivity implements Animation.AnimationListener {

    private LinearLayout ActionLL;
    private ImageView imgLogo;
    private Animation splashLogoAnimation = null;

    //file system ;
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        InitViews();

    }

    private void InitViews() {
        ActionLL = (LinearLayout) findViewById(R.id.ActionLL);
        ActionLL.setVisibility(View.GONE);
        imgLogo = (ImageView) findViewById(R.id.imgLogo);
        splashLogoAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_animation);
        splashLogoAnimation.setAnimationListener(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imgLogo.startAnimation(splashLogoAnimation);
            }
        }, 3000);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        String status = sharedPreferences.getString("loggedIn","");
        if(status.equals("yes")) {

            Intent i = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(i);
            finish();
        }
        else{

        ActionLL.setVisibility(View.VISIBLE);
        ActionLL.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in_animation));
    }
    }




    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public void Signup(View v) {
        GotoNextActivity(this, SignupActivity.class);
    }

    public void Skip(View v) {
        GotoNextActivityClearTop(this, HomeActivity.class);
    }

    public void Login(View view) {
        GotoNextActivity(this, LoginActivity.class);
    }
}
