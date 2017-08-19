package com.example.Gifts.Gift;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Gift_Show;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class Gift_Loader extends View {

    Activity context;
    ListView list;
    ArrayList<Person_Class> person = MyActivity.getPerson();
    ArrayList<Group_Class> group = MyActivity.getGroup();
    String personId;
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<Gift_Class> giftlist;


    public Gift_Loader(Context context, String personId) {

        super(context);
        this.context = (Activity) context;
        this.personId = personId;
        getGifts(personId);
        init();
    }

    public void init() {

        if (giftlist.size() != 0) {
            GiftList adapter = new GiftList(context, person, giftlist);
            list = (ListView) context.findViewById(R.id.groupListViewg);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(context, "You Clicked at " + giftlist.get(position).getName(), Toast.LENGTH_SHORT).show();

                    String name = giftlist.get(position).getName();
                    String budget = giftlist.get(position).getAmount();
                    String store = giftlist.get(position).getStore();
                    String status = giftlist.get(position).getStatus();
//                Group g=new Group(context);
                    Intent i = new Intent(context, Gift_Show.class);
                    i.putExtra("name", name);
                    i.putExtra("budget", budget);
                    i.putExtra("store", store);
                    i.putExtra("status", status);
                    i.putExtra("id", giftlist.get(position).getId());
                    i.putExtra("personId", personId);
                    context.startActivity(i);
                }
            });
        }

    }

    public ArrayList<Gift_Class> getGifts(String id) {
        giftlist = new ArrayList<Gift_Class>();
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getPersonName().equals(id)) {
                if (gift.get(i).getStatus().equals("Not purchased")) {
                    giftlist.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getPersonName().equals(id)) {
                if (gift.get(i).getStatus().equals("Ordered")) {
                    giftlist.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getPersonName().equals(id)) {
                if (gift.get(i).getStatus().equals("Shipped")) {
                    giftlist.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getPersonName().equals(id)) {
                if (gift.get(i).getStatus().equals("Purchased")) {
                    giftlist.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getPersonName().equals(id)) {
                if (gift.get(i).getStatus().equals("Wrapped")) {
                    giftlist.add(gift.get(i));
                }
            }
        }
        return giftlist;
    }
}