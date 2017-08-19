package com.example.Gifts.Classes;

/**
 * Created by adminslt on 11/30/2014.
 */
public class Gift_List_Class {
    String id;
    String name;
    String amount;
    String status;
    String store;
    String image;

    public Gift_List_Class(String id, String name, String amount, String status, String image, String store) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.status = status;
        this.store = store;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
