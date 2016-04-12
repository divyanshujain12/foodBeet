package com.elgroup.foodbeat;

import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.elgroup.foodbeat.Adapters.VendorLocationAdapter;
import com.elgroup.foodbeat.CustomViews.SearchHeaderView;
import com.elgroup.foodbeat.Models.VendorLocationModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class VendorsLocationMap extends BaseActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private VendorLocationAdapter vendorLocationAdapter;
    private RecyclerView vendorsRV;
    private ArrayList<VendorLocationModel> vendorLocationModels;
    private Toolbar toolbar;
    private AppBarLayout appbar;
    private SearchHeaderView searchHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_vendors_location_map);
        InitView();
    }

    private void InitView() {
        initToolbar();
        InitMap();

        vendorsRV = (RecyclerView) findViewById(R.id.vendorsRV);
        vendorsRV.setLayoutManager(new LinearLayoutManager(this));
        vendorLocationModels = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            VendorLocationModel vendorLocationModel = new VendorLocationModel();
            vendorLocationModel.setName("Adams Shop");
            vendorLocationModel.setAddress("23 Town St");
            vendorLocationModel.setDistance("2");
            vendorLocationModel.setImage_url("http://www.pachd.com/free-images/food-images/strawberries-02.jpg");

            vendorLocationModels.add(vendorLocationModel);
        }

        vendorLocationAdapter = new VendorLocationAdapter(this, vendorLocationModels);
        vendorsRV.setAdapter(vendorLocationAdapter);
    }

    private void initToolbar() {

        /*searchHeader = (SearchHeaderView) findViewById(R.id.searchHeader);
        searchHeader.InitViews(this);*/
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) appbar.getLayoutParams();
        AppBarLayout.Behavior behavior = new AppBarLayout.Behavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(AppBarLayout appBarLayout) {
                return false;
            }
        });
        layoutParams.setBehavior(behavior);
    }

    private void InitMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                LatLng myLocation = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(myLocation).title("Your Current Location"));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 12.0f));
            }
        });
    }

    public void moveBack(View view) {
        onBackPressed();
    }
}
