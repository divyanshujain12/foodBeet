package com.elgroup.foodbeat.Models;

import java.util.ArrayList;

/**
 * Created by Lenovo on 28-03-2016.
 */
public class HomeFragmentModel {
    String id;
    ArrayList<String> images;
    String name;
    String price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
