package com.elgroup.foodbeat;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

/**
 * Created by Lenovo on 31-03-2016.
 */
public class LoginActivity extends BaseActivity implements CallBackInterface {

    @Bind(R.id.userNametxt)
    EditText usernameEt;
    @Bind(R.id.passwordtxt)
    EditText passwordEt;
    @Bind(R.id.txtSignin)
    TextView txtLogin;

    public static String  GCM_reg_id;
    LinearLayout mainLayout;
    private final BroadcastReceiver mHandleMessageReceiver1 = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            MyApplication.acquireWakeLock(getApplicationContext());
            MyApplication.releaseWakeLock();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        ButterKnife.bind(this);
        new StartNotificationAsync().execute();
        InitViews();

    }

    private void InitViews() {

    }

    public void Login(View v){

        if(usernameEt.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Username can't be empty!");
            return;
        }
        if(passwordEt.getText().toString().equals("")) {
            CommonFunctions.showSnackBarWithoutAction(mainLayout, "Password can't be empty!");
            return;
        }

        String gcm_id = sharedPreferences.getString("GCM_REGISTERED_ID","");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("username", usernameEt.getText().toString());
        params.put("userpass", passwordEt.getText().toString());
        params.put("devicetoken",gcm_id);
        params.put("devicetype","A");

        String url = Constants.WebServices.LOG_IN;
        CallWebService.getInstance(LoginActivity.this, true).hitJSONObjectVolleyWebService(Request.Method.POST, url, params, LoginActivity.this);


    }

    @Override
    public void onJsonObjectSuccess(JSONObject object) {

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

            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(i);
            finish();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onJsonArrarSuccess(JSONArray array) {

    }

    @Override
    public void onFailure(String str) {

    }

    class StartNotificationAsync extends AsyncTask<String, String, String> {


        private ProgressDialog pdia;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pdia = new ProgressDialog(LoginActivity.this);
            pdia.setMessage("Loading...");
            pdia.show();
        }

        @Override
        protected String doInBackground(String... params) {


            String registrationId = sharedPreferences.getString("GCM_REGISTERED_ID", "");
            if (registrationId.isEmpty()) {
                try {
                    if (cdr.isConnectingToInternet()) {

                        GCMRegistrar.checkDevice(LoginActivity.this);
                        GCMRegistrar.checkManifest(LoginActivity.this);

                        registerReceiver(mHandleMessageReceiver1, new IntentFilter(
                                Constants.DISPLAY_MESSAGE_ACTION));
                        String regId = GCMRegistrar.getRegistrationId(LoginActivity.this);
                        if (regId.equals("")) {
                            GCMRegistrar.register(LoginActivity.this, Constants.GOOGLE_SENDER_ID);


                        } else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("GCM_REGISTERED_ID", regId);
                            editor.commit();
                            if (GCMRegistrar.isRegisteredOnServer(LoginActivity.this)) {
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

        @Override
        protected void onPostExecute(String result) {

            pdia.dismiss();

        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub

        try{
            if(mHandleMessageReceiver1!=null)
                unregisterReceiver(mHandleMessageReceiver1);
        }catch(Exception e)
        {

        }
        super.onDestroy();

    }
}
