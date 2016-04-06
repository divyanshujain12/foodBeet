package com.elgroup.foodbeat.BasketFragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.elgroup.foodbeat.CustomViews.HeaderView;
import com.elgroup.foodbeat.Models.BasketModel;
import com.elgroup.foodbeat.R;
import com.elgroup.foodbeat.Utils.CommonFunctions;
import com.elgroup.foodbeat.Utils.ImageLoading;
import com.neopixl.pixlui.components.edittext.EditText;
import com.neopixl.pixlui.components.textview.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 04-04-2016.
 */
public class BasketParentFragment extends Fragment implements View.OnClickListener {
    private HeaderView headerView;
    private ArrayList<BasketModel> basketModels;
    private View noItemView;
    private LinearLayout productsLL;
    private ImageLoading imageLoading;
    private TextView txtPaymentMethod, txtAddCards, txtPayPal, txtPaytm, txtPayMasterPass;
    private LinearLayout paymentMethodsLL, savedCardsLL, cardsLL;

    private LinearLayout addressLL;
    private TextView txtSelectAddress,txtNoAddress, txtShippingAddress, txtEdit;
    private EditText edtAddress;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.basket_parent_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InitViews();
    }

    private void InitViews() {

        headerView = (HeaderView) getView().findViewById(R.id.headerView);
        noItemView = getView().findViewById(R.id.noItemView);
        basketModels = new ArrayList<>();
        productsLL = (LinearLayout) getView().findViewById(R.id.productsLL);
        imageLoading = new ImageLoading(getContext(), 10);
        txtPaymentMethod = (TextView) getView().findViewById(R.id.txtPaymentMethod);
        txtPaymentMethod.setOnClickListener(this);

        txtAddCards = (TextView) getView().findViewById(R.id.txtAddCards);
        txtPayPal = (TextView) getView().findViewById(R.id.txtPayPal);
        txtPaytm = (TextView) getView().findViewById(R.id.txtPaytm);
        txtPayMasterPass = (TextView) getView().findViewById(R.id.txtPayMasterPass);

        paymentMethodsLL = (LinearLayout) getView().findViewById(R.id.paymentMethodsLL);
        savedCardsLL = (LinearLayout) getView().findViewById(R.id.savedCardsLL);
        cardsLL = (LinearLayout) getView().findViewById(R.id.cardsLL);

        txtSelectAddress = (TextView) getView().findViewById(R.id.txtSelectAddress);
        txtSelectAddress.setOnClickListener(this);
        addressLL = (LinearLayout) getView().findViewById(R.id.addressLL);
        txtNoAddress = (TextView) getView().findViewById(R.id.txtNoAddress);
        txtShippingAddress = (TextView) getView().findViewById(R.id.txtShippingAddress);
        txtEdit = (TextView) getView().findViewById(R.id.txtEdit);

        edtAddress = (EditText) getView().findViewById(R.id.edtAddress);

        setUpArrayList();
        addProductItem();
        if (basketModels.size() == 0) {
            noItemView.setVisibility(View.VISIBLE);
            headerView.setHeaderText(getString(R.string.basket));
        } else {
            noItemView.setVisibility(View.GONE);
            headerView.setHeaderText(getString(R.string.shopping_cart));
        }
    }

    private void setUpArrayList() {
        basketModels = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            BasketModel basketModel = new BasketModel();
            basketModel.setProduct_image("http://www.pachd.com/free-images/food-images/strawberries-02.jpg");
            basketModel.setProduct_price("$20");
            basketModel.setProduct_name("Strawberry");
            basketModel.setDescription("Lorem Ipsum Dollar sit amet.");
            basketModels.add(basketModel);
        }
    }

    private void addProductItem() {
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        ImageView productImage;
        TextView txtProductName, txtDescription, txtPrice;
        for (BasketModel basketModel : basketModels) {
            View view = layoutInflater.inflate(R.layout.basket_products_item, null);
            productImage = (ImageView) view.findViewById(R.id.productImage);
            txtProductName = (TextView) view.findViewById(R.id.txtProductName);
            txtDescription = (TextView) view.findViewById(R.id.txtDescription);
            txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            imageLoading.LoadImage(basketModel.getProduct_image(), productImage, null);
            txtProductName.setText(basketModel.getProduct_name());
            txtDescription.setText(basketModel.getDescription());
            txtPrice.setText(basketModel.getProduct_price());

            productsLL.addView(view);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtPaymentMethod:
                openOrCloseViews(txtPaymentMethod, paymentMethodsLL);
                break;
            case R.id.txtSelectAddress:
                openOrCloseViews(txtSelectAddress,addressLL);
                break;
        }
    }

    private void openOrCloseViews(TextView textView, View changeView) {
        if (changeView.getVisibility() == View.VISIBLE) {
            textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf"));
            textView.setTextColor(Color.BLACK);
            CommonFunctions.collapse(changeView);
        } else {
            textView.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Bold.ttf"));
            textView.setTextColor(getResources().getColor(R.color.colorPrimary));
            CommonFunctions.expand(changeView);
        }
    }
}
