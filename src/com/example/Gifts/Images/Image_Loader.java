package com.example.Gifts.Images;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.Gifts.AddPerson1;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class Image_Loader extends View {

    public ArrayList<Group_Class> group = MyActivity.getGroup();
    Activity context;
    ListView list;
    Integer[] imageId = MyActivity.getImageId();
    String name;
    String budget;
    int id;

    public Image_Loader(Context context, String name, String budget, int id) {

        super(context);
        this.context = (Activity) context;
        this.name = name;
        this.budget = budget;
        this.id = id;
        init();
    }

    public void init() {

        ImageList adapter = new ImageList(context, imageId);
        list = (ListView) context.findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String image1 = "" + position;
                Intent in = new Intent(context, AddPerson1.class);
                in.putExtra("image", image1);
                in.putExtra("name", name);
                in.putExtra("budget", budget);
                in.putExtra("id", id);
                context.startActivity(in);
//                context.onBackPressed();
//                onBackPress();
//                context.setContentView(R.layout.add_person);

            }
        });


    }

    public void loadSpinner() {
        String arr[] = new String[group.size()];
        for (int i = 0; i < group.size(); i++) {
            arr[i] = group.get(i).getName();
        }
        Spinner s = (Spinner) findViewById(R.id.groupNamea_d);
        ArrayAdapter adapter = new ArrayAdapter(context,
                android.R.layout.simple_spinner_item, arr);
        s.setAdapter(adapter);
    }

}