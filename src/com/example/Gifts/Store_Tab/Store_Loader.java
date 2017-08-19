package com.example.Gifts.Store_Tab;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class Store_Loader extends View {

    MyActivity context;
    ListView list;
    ArrayList<Store_Class> store;

    public Store_Loader(Context context) {
        super(context);
        this.context = (MyActivity) context;
        this.store = MyActivity.store;
        init();
    }


    public void init() {

        StoreList adapter = new StoreList(context, store);
        list = (ListView) context.findViewById(R.id.storelistView);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "You Clicked at " + store.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent in = new Intent(context, StoreDetails.class);
                in.putExtra("storeName", store.get(position).getName());
                in.putExtra("storeId", store.get(position).getId());
                context.startActivity(in);

            }
        });
    }

}