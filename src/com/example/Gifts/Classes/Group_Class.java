package com.example.Gifts.Classes;

/**
 * Created by Anosh on 11/26/2014.
 */
public class Group_Class extends Classes {
    String budget;


    public Group_Class(String id, String name, String image, String budget) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.budget = budget;


    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }


}
