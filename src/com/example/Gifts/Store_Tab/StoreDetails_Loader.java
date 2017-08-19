package com.example.Gifts.Store_Tab;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class StoreDetails_Loader extends View {

    public static Activity context;
    ListView list;

    ArrayList<Gift_Class> gift = MyActivity.getGift();
    String storeId;
    ArrayList<Gift_Class> storeGift;

    public StoreDetails_Loader(Context context, String storeId) {
        super(context);
        StoreDetails_Loader.context = (Activity) context;
        this.storeId = storeId;
        init();
    }

    public void init() {

        StoreDetailsList adapter = new StoreDetailsList(context, getStoreGift(storeId));
        list = (ListView) context.findViewById(R.id.store_detailslistView);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(context, "You Clicked at " + getPersonName(personIds.get(position)), Toast.LENGTH_SHORT).show();
//
//                String name=getPersonName(personIds.get(position));
//                String budget=getPersonBudget(personIds.get(position));
//                String personId=personIds.get(position);
////                Group g=new Group(context);
//                Intent i=new Intent(context,Group.class);
//                i.putExtra("name",name);
//                i.putExtra("budget",budget);
//                i.putExtra("id",personId);
//                context.startActivity(i);
            }
        });

    }

    public ArrayList<Gift_Class> getStoreGift(String storeId) {
        storeGift = new ArrayList<Gift_Class>();
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStore().equals(storeId)) {
                if (gift.get(i).getStatus().equals("Not purchased")) {
                    storeGift.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStore().equals(storeId)) {
                if (gift.get(i).getStatus().equals("Purchased")) {
                    storeGift.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStore().equals(storeId)) {
                if (gift.get(i).getStatus().equals("Ordered")) {
                    storeGift.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStore().equals(storeId)) {
                if (gift.get(i).getStatus().equals("Shipped")) {
                    storeGift.add(gift.get(i));
                }
            }
        }
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStore().equals(storeId)) {
                if (gift.get(i).getStatus().equals("Wrapped")) {
                    storeGift.add(gift.get(i));
                }
            }
        }
        return storeGift;
    }
}