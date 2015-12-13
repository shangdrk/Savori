package com.zoray.savori.data;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;

import java.util.Date;

@ParseClassName("Transaction")
public class Transaction extends ParseObject {

    public Transaction() {}

    public String getFoodTitle() {
        String title = null;
        try {
            title = getParseObject("food").fetchIfNeeded().getString("dishName");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return title;
    }

    public String getFoodSeller() {
        String firstName = null;
        String lastName = null;
        try {
            firstName = getParseObject("seller").fetchIfNeeded().getString("firstName");
            lastName = getParseObject("seller").fetchIfNeeded().getString("lastName");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return firstName + " " + lastName;
    }

    public int getPrice() {
        return (int) getNumber("price");
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
            foodImage = getParseObject("food").fetchIfNeeded()
                    .getParseFile("picture").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return foodImage;
    }

    public byte[] getSellerImage() {
        byte[] sellerImage = null;
        try {
            sellerImage = getParseObject("seller").fetchIfNeeded()
                    .getParseFile("picture").getData();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sellerImage;
    }
}
