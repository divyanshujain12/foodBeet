package com.elgroup.foodbeat.Models;


import java.util.ArrayList;


public class SaleFragmentModel {
    String id;
    String categoryId;
    String featured;
    String status;
    String adddate;
    String sellerid;
    String category;
    ArrayList<String> images;
    String name;
    String price;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getcategoryId() {
        return categoryId;
    }
    public void setcategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getfeatured() {
        return featured;
    }
    public void setfeatured(String featured) {
        this.featured = featured;
    }

    public String getstatus() {
        return status;
    }
    public void setstatus(String status) {
        this.status = status;
    }

    public String getadddate() {
        return adddate;
    }
    public void setadddate(String adddate) {
        this.adddate = adddate;
    }

    public String getsellerid() {
        return sellerid;
    }
    public void setsellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getcategory() {
        return category;
    }
    public void setcategory(String category) {
        this.category = category;
    }


    public ArrayList<String> getImages() {
        return images;
    }
    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
}
