package com.example.Gifts.Classes;

/**
 * Created by Anosh on 11/26/2014.
 */
public class Person_Class extends Classes {
    String group;
    String budget;
    String contact;

    public Person_Class(String id, String name, String image, String budget, String group, String contact) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.budget = budget;
        this.group = group;
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
