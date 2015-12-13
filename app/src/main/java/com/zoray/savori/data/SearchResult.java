package com.zoray.savori.data;

import android.graphics.Bitmap;

public class SearchResult {

    private String name;
    private String parseId;
    private String price;
    private byte[] dishImage = null;

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    public byte[] getDishImage() {
        return dishImage;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setParseId(String id) {
        this.parseId = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getParseId() {
        return parseId;
    }

    public SearchResult() {

    }
}
