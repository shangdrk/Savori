package com.zoray.savori.data;

/**
 * Created by amandayin on 12/12/15.
 */
public class Dish {

    private String dishName;
    private String price;
    private String parseID;

    public Dish(String dishName, String price, String parseID){
        this.dishName = dishName;
        this.parseID = parseID;
        this.price = price;
    }

    public String getParseID(){
        return parseID;
    }


}
