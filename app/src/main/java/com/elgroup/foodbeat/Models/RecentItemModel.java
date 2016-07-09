package com.elgroup.foodbeat.Models;

/**
 * Created by Lenovo on 30-03-2016.
 */
public class RecentItemModel {
    String product_image;
    String product_name;
    String product_price;
    String product_old_price;
    String id;

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_old_price() {
        return product_old_price;
    }

    public void setProduct_old_price(String product_old_price) {
        this.product_old_price = product_old_price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
