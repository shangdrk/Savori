package com.zoray.savori.data;

public class HistoryRow {

    private String foodTitle;
    private String foodSeller;
    private double price;
    private long orderTime;
    // Remember to also add images

    public HistoryRow() {
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public String getFoodSeller() {
        return foodSeller;
    }

    public void setFoodSeller(String foodSeller) {
        this.foodSeller = foodSeller;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }
}
