package com.elgroup.foodbeat;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 18-03-2016.
 */
public class SignupActivity extends BaseActivity {

    @Bind(R.id.imgCancel)
    ImageView imgCancel;
    @Bind(R.id.usernameET)
    EditText usernameET;
    @Bind(R.id.passwordET)
    EditText passwordET;
    @Bind(R.id.confPasswordET)
    EditText confPasswordET;
    @Bind(R.id.emailET)
    EditText emailET;
    @Bind(R.id.sellerLL)
    LinearLayout sellerLL;
    @Bind(R.id.buyerLL)
    LinearLayout buyerLL;
    @Bind(R.id.deliveryBoyLL)
    LinearLayout deliveryBoyLL;
    @Bind(R.id.laborLL)
    LinearLayout laborLL;
    @Bind(R.id.txtLogin)
    TextView txtLogin;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        ButterKnife.bind(this);

        InitViews();

    }

    private void InitViews() {

    }

    public void Cancel(View v) {
        finish();
    }

    @OnClick({R.id.imgCancel, R.id.sellerLL, R.id.buyerLL, R.id.deliveryBoyLL, R.id.laborLL, R.id.txtLogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imgCancel:
                break;
            case R.id.sellerLL:
                break;
            case R.id.buyerLL:
                break;
            case R.id.deliveryBoyLL:
                break;
            case R.id.laborLL:
                break;
            case R.id.txtLogin:
                break;
        }
    }
}
