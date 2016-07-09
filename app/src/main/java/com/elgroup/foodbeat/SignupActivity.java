package com.elgroup.foodbeat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.android.volley.Request;
import com.elgroup.foodbeat.Utils.CallBackInterface;
import com.elgroup.foodbeat.Utils.CallWebService;
import com.elgroup.foodbeat.Utils.CommonFunctions;
import com.elgroup.foodbeat.Utils.Constants;
import com.google.android.gcm.GCMRegistrar;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 18-03-2016.
 */
public class SignupActivity extends BaseActivity implements CallBackInterface {

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


    String userType="B", GCM_reg_id;
    LinearLayout mainLayout;
    private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MyApplication.acquireWakeLock(getApplicationContext());
            MyApplication.releaseWakeLock();
        }
    };

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

        new StartNotificationAsync().execute();
        InitViews();

    }

    private void InitViews() {

        mainLayout = (LinearLayout) findViewById(R.id.mainSignuplayout);
    }

    public void Cancel(View v) {
        finish();
    }

    public void toggleColor(String type)
    {
        if(type.contains("seller"))
        {
            userType ="S";
            sellerLL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            buyerLL.setBackgroundColor(Color.TRANSPARENT);
            deliveryBoyLL.setBackgroundColor(Color.WHITE);
            laborLL.setBackgroundColor(Color.WHITE);
        }
        else if(type.contains("buyer"))
        {
            userType ="B";
            sellerLL.setBackgroundColor(Color.WHITE);
            buyerLL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            deliveryBoyLL.setBackgroundColor(Color.WHITE);
            laborLL.setBackgroundColor(Color.WHITE);
        }
        else if(type.contains("delivery"))
        {
            userType ="D";
            sellerLL.setBackgroundColor(Color.WHITE);
            buyerLL.setBackgroundColor(Color.TRANSPARENT);
            deliveryBoyLL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            laborLL.setBackgroundColor(Color.WHITE);
        }
        else if(type.contains("labour"))
        {
            userType ="L";
            sellerLL.setBackgroundColor(Color.WHITE);
            buyerLL.setBackgroundColor(Color.TRANSPARENT);
            deliveryBoyLL.setBackgroundColor(Color.WHITE);
            laborLL.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        }

    }

    public void signUp()
    {

        if(usernameET.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Username can't be empty!");
            return;
        }
        if(passwordET.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Password can't be empty!");
            return;
        }
        if(confPasswordET.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Please confirm Password");
            return;
        }
        if(emailET.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Email can't be empty!");
            return;
        }
        if(!confPasswordET.getText().toString().equals(passwordET.getText().toString())) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Passwords do not match !");
            return;
        }
        String gcm_id = sharedPreferences.getString("GCM_REGISTERED_ID","");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", usernameET.getText().toString());
        params.put("useremail",emailET.getText().toString());
        params.put("userpass", passwordET.getText().toString());
        params.put("devicetoken",gcm_id);
        params.put("devicetype","A");
        params.put("usertype",userType);

        String url = Constants.WebServices.SIGN_UP;
        CallWebService.getInstance(SignupActivity.this, true).hitJSONObjectVolleyWebService(Request.Method.POST, url, params, SignupActivity.this);


    }


    @OnClick({R.id.imgCancel, R.id.sellerLL, R.id.buyerLL, R.id.deliveryBoyLL, R.id.laborLL, R.id.txtLogin})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.imgCancel:
                break;
            case R.id.sellerLL:
                toggleColor("seller");
                break;
            case R.id.buyerLL:
                toggleColor("buyer");
                break;
            case R.id.deliveryBoyLL:
                toggleColor("delivery");
                break;
            case R.id.laborLL:
                toggleColor("labour");
                break;
            case R.id.txtLogin:
                signUp();
                break;
        }
    }

    @Override
    public void onJsonObjectSuccess(JSONObject object) {

        Log.i("Signup res obj", object.toString());
      //  {"error":"0","msg":"Successfully Registered",
      // "user":{"userid":20,"username":"agg","useremail":"aa@ds.gg","usertype":"B",
      // "accesstoken":"ecb98638a56763b835f28d65a11a4ec7"}}

        try {

            JSONObject user = object.getJSONObject("user");
            String userName = user.getString("username");
            String userid =   user.getString("userid");
            String useremail =   user.getString("useremail");
            String usertype =   user.getString("usertype");
            String accesstoken =   user.getString("accesstoken");


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("userName", userName);
            editor.putString("userid", userid);
            editor.putString("useremail", useremail);
            editor.putString("usertype", usertype);
            editor.putString("accesstoken", accesstoken);
            editor.putString("loggedIn", "yes");
            editor.commit();

            Intent i = new Intent(SignupActivity.this, HomeActivity.class);
            startActivity(i);
            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onJsonArrarSuccess(JSONArray array) {

        Log.i("Signup res", array.toString());
    }

    @Override
    public void onFailure(String str) {

        Log.i("Signup res", str);

    }


    class StartNotificationAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String registrationId = sharedPreferences.getString("GCM_REGISTERED_ID", "");
            if (registrationId.isEmpty()) {
                try {
                    if (cdr.isConnectingToInternet()) {

                        GCMRegistrar.checkDevice(SignupActivity.this);
                        GCMRegistrar.checkManifest(SignupActivity.this);

                        registerReceiver(mHandleMessageReceiver, new IntentFilter(
                                Constants.DISPLAY_MESSAGE_ACTION));
                        String regId = GCMRegistrar.getRegistrationId(SignupActivity.this);
                        if (regId.equals("")) {
                            GCMRegistrar.register(SignupActivity.this, Constants.GOOGLE_SENDER_ID);


                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("GCM_REGISTERED_ID", regId);
                            editor.commit();
                            if (GCMRegistrar.isRegisteredOnServer(SignupActivity.this)) {
                               //MyApplication.register(regId);
                                GCM_reg_id = regId;
                                System.out.println("reg id" +regId);
                            }
                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("GCM excp" +e);
                }
            } else {
                GCM_reg_id = registrationId;
               // MyApplication.register(registrationId);
            }
            return null;
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(mHandleMessageReceiver!=null)
                unregisterReceiver(mHandleMessageReceiver);
        }catch(Exception e)
        {

        }
        super.onDestroy();

    }
}
