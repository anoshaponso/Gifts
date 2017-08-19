package com.example.Gifts.Group_Tab;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Group;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class GroupPeople_Loader extends View {

    public static MyActivity context;
    public ArrayList<Person_Class> person;
    ListView list;
    ArrayList<String> personIds;
    ArrayList<Group_Class> group;

    public GroupPeople_Loader(Context context, ArrayList<String> personIds, ArrayList<Group_Class> group) {
        super(context);
        GroupPeople_Loader.context = (MyActivity) context;
        this.personIds = personIds;
        this.person = MyActivity.person;
        this.group = group;
        init();
    }

    public GroupPeople_Loader(Context context, ArrayList<Group_Class> group) {
        super(context);
        GroupPeople_Loader.context = (MyActivity) context;
        this.person = MyActivity.person;
        this.group = group;
        init1();
    }

    public void init1() {

        GroupPeopleList1 adapter = new GroupPeopleList1(context, person);
        list = (ListView) context.findViewById(R.id.group_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "You Clicked at " + (person.get(position).getName()), Toast.LENGTH_SHORT).show();
                String name = getPersonName(person.get(position).getId());
                String budget = getPersonBudget(person.get(position).getId());
                String personId = person.get(position).getId();

                Intent i = new Intent(context, Group.class);
                i.putExtra("name", name);
                i.putExtra("budget", budget);
                i.putExtra("id", personId);
                context.startActivity(i);
            }
        });

    }


    public void init() {

        GroupPeopleList adapter = new GroupPeopleList(context, personIds, person);
        list = (ListView) context.findViewById(R.id.group_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "You Clicked at " + getPersonName(personIds.get(position)), Toast.LENGTH_SHORT).show();
                String name = getPersonName(personIds.get(position));
                String budget = getPersonBudget(personIds.get(position));
                String personId = personIds.get(position);

                Intent i = new Intent(context, Group.class);
                i.putExtra("name", name);
                i.putExtra("budget", budget);
                i.putExtra("id", personId);
                context.startActivity(i);
            }
        });

    }

    public String getPersonName(String id) {
        String name = null;
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getId())) {
                name = person.get(i).getName();
                break;
            }
        }
        return name;
    }

    public String getPersonBudget(String id) {
        String budget = null;
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getId())) {
                budget = person.get(i).getBudget();
                break;
            }
        }
        return budget;
    }
}