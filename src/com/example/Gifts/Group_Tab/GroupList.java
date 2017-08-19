package com.example.Gifts.Group_Tab;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.R;

import java.util.ArrayList;

/**
 * Created by Anosh on 11/11/2014.
 */
public class GroupList extends ArrayAdapter<Group_Class> {
    private final Activity context;


    ArrayList<Person_Class> person;
    ArrayList<Group_Class> group;
    String groupID;
    ArrayList<String> personID;


    public GroupList(Activity context, ArrayList<Person_Class> person, ArrayList<Group_Class> group) {
        super(context, R.layout.group_list_listview, group);
        this.person = person;
        this.context = context;
        this.group = group;


    }

    public String getGroupName(String id) {
        String groupName = null;
        for (int i = 0; i < group.size(); i++) {
            if (group.get(i).getId().equals(id)) {
                groupName = group.get(i).getName();
                break;
            }
        }
        return groupName;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.group_list_listview, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.groupName);

        txtTitle.setText(group.get(position).getName());


        GroupPeopleList adapter = new GroupPeopleList(context, getGroup_Data1(group.get(position).getId()), person);
        ListView ls = (ListView) rowView.findViewById(R.id.groupListView);
        ls.setAdapter(adapter);

        return rowView;
    }

    public ArrayList<String> getGroup_Data1(String group1) {


        personID = new ArrayList<String>();
        for (int j = 0; j < person.size(); j++) {
            if (group1.equals(person.get(j).getGroup())) {
                personID.add((person.get(j).getId()));
            }
        }

        return personID;


    }
}