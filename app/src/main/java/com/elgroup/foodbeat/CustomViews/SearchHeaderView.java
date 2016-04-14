package com.elgroup.foodbeat.CustomViews;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.elgroup.foodbeat.R;
import com.neopixl.pixlui.components.edittext.EditText;

/**
 * Created by Lenovo on 06-04-2016.
 */

public class SearchHeaderView extends RelativeLayout implements View.OnClickListener {

    ImageView searchIV, back;
    EditText edtSearch;
    Toolbar toolbar;
    Context context;
    final String xmlns = "http://schemas.android.com/apk/res-auto";
    public SearchHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public void InitViews(Context context) {
        this.context = context;
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.search_header_view, this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        edtSearch = (EditText) findViewById(R.id.edtSearch);
        back = (ImageView) view.findViewById(R.id.back);
        searchIV = (ImageView) view.findViewById(R.id.searchIV);
        back.setOnClickListener(this);
    }

    public EditText getEditText() {
        return edtSearch;
    }

    public ImageView getBackButton() {
        return back;
    }

    public ImageView getSearchButton() {
        return searchIV;
    }

    public String getHeaderEditTexttext() {
        return edtSearch.getText().toString();
    }

    public Toolbar getToolBar() {
        return toolbar;
    }

    @Override
    public void onClick(View view) {
        ((Activity)context).onBackPressed();
    }


}
