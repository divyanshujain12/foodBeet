package com.elgroup.foodbeat.CustomViews;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.VendorsLocationMap;
import com.neopixl.pixlui.components.textview.TextView;

/**
 * Created by Lenovo on 04-04-2016.
 */
public class HeaderView extends RelativeLayout implements OnClickListener {
    private TextView txtHeaderName;
    private ImageView locationIV;

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        InitViews();
    }


    private void InitViews() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.header_view, this);

        txtHeaderName = (TextView) view.findViewById(R.id.txtHeaderName);
        locationIV = (ImageView) view.findViewById(R.id.locationIV);
        locationIV.setOnClickListener(this);
    }

    public void setHeaderText(String text) {
        txtHeaderName.setText(text);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), VendorsLocationMap.class);
        getContext().startActivity(intent);
    }
}
