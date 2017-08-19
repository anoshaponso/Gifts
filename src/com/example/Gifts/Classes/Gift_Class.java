package com.example.Gifts.Classes;

/**
 * Created by Anosh on 11/26/2014.
 */
public class Gift_Class extends Classes {
    String amount;
    String status;
    String store;
    String personName;

    public Gift_Class(String id, String name, String amount, String personName, String status, String image, String store) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.amount = amount;
        this.personName = personName;
        this.status = status;
        this.store = store;

    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
