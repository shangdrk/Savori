package com.zoray.savori.data;

/**
 * Created by amandayin on 12/12/15.
 */
public class Dish {

    private String dishName;
    private String price;
    private String parseID;

    public Dish() {

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
