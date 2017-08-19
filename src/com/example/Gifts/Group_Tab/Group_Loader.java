package com.example.Gifts.Group_Tab;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class Group_Loader extends View {

    MyActivity context;
    ListView list;
    ArrayList<Person_Class> person;
    ArrayList<Group_Class> group;

    public Group_Loader(Context context) {

        super(context);
        this.context = (MyActivity) context;
        this.person = MyActivity.person;
        this.group = MyActivity.group;
        init();
    }

    public void init() {

        GroupList adapter = new GroupList(context, person, group);
        list = (ListView) context.findViewById(R.id.group_list);
        list.setAdapter(adapter);


    }

}