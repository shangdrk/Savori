package com.zoray.savori.data;

import android.graphics.Bitmap;

import com.parse.ParseUser;

/**
 * Created by amandayin on 12/12/15.
 */
public class Dish {

    private String dishName;
    private String price;
    private String parseID;
    private ParseUser chef;
    private byte[] dishImage = null;

    public Dish() {

    }

    public byte[] getDishImage() {
        return dishImage;
    }

    public void setDishImage(byte[] dishImage) {
        this.dishImage = dishImage;
    }

    public ParseUser getChef() {
        return chef;
    }

    public void setChef(ParseUser chef) {
        this.chef = chef;
    }

    public String getDishName() {
        return this.dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setParseID(String parseID) {
        this.parseID = parseID;
    }

    public String getParseID(){
        return parseID;
    }

}
