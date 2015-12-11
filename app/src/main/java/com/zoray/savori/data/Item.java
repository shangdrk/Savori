package com.zoray.savori.data;

/**
 * Created by amandayin on 12/10/15.
 */
public class Item {

    private String name;
    private boolean bought;

    public void setName(String name) {
        this.name = name;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public String getName() {

        return name;
    }

    public boolean isBought() {
        return bought;
    }

    public Item(String name, boolean bought) {

        this.name = name;
        this.bought = bought;
    }
}
