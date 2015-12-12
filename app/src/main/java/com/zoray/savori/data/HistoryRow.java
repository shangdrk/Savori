package com.zoray.savori.data;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Transaction")
public class HistoryRow extends ParseObject {

    public HistoryRow() {}

    public String getFoodTitle() {
        return getParseObject("food").getString("dishName");
    }

    public String getFoodSeller() {
        return getParseObject("seller").getString("firstName") + " " +
                getParseObject("seller").getString("lastName");
    }

    public double getPrice() {
        return (double) getNumber("price");
    }

    public Date getOrderTime() {
        return getDate("orderTime");
    }

    public boolean getIsFinished() {
        return getBoolean("isFinished");
    }

    public byte[] getFoodImage() {
        byte[] foodImage = null;
        try {
            foodImage = getParseObject("food").getParseFile("picture").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return foodImage;
    }

    public byte[] getSellerImage() {
        byte[] sellerImage = null;
        try {
            sellerImage = getParseObject("seller").getParseFile("picture").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sellerImage;
    }
}
