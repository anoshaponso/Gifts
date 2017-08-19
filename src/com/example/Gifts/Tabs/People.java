package com.example.Gifts.Tabs;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Group_Tab.GroupPeople_Loader;
import com.example.Gifts.Group_Update;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by adminslt on 11/25/2014.
 */
public class People extends Fragment {
    Spinner s;
    ArrayList<Group_Class> group = MyActivity.getGroup();
    ArrayList<Person_Class> person = MyActivity.getPerson();
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<String> personID;
    TextView spentPeople;
    TextView budgetPeople;
    TextView remainPeople;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.people, container, false);
        TextView daysRemain = (TextView) rootView.findViewById(R.id.daysRemain);
        daysRemain.setText(getDateCount() + " Shopping Days Left ");
        s = (Spinner) rootView.findViewById(R.id.groupSpinner);
        loadSpinner1();
        spentPeople = (TextView) rootView.findViewById(R.id.spentpeople);
        budgetPeople = (TextView) rootView.findViewById(R.id.budgetpeople);
        remainPeople = (TextView) rootView.findViewById(R.id.remainpeople);

        return rootView;
    }

    public String getDateCount() {
        Calendar thatDay = Calendar.getInstance();
        thatDay.set(Calendar.DAY_OF_MONTH, 25);
        thatDay.set(Calendar.MONTH, 11); // 0-11 so 1 less
        thatDay.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));

        Calendar today = Calendar.getInstance();

        long diff = thatDay.getTimeInMillis() - today.getTimeInMillis();
        return String.valueOf((diff) / (24 * 60 * 60 * 1000));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    public void loadSpinner1() {
        String arr[] = new String[MyActivity.group.size() + 1];
        arr[0] = "All";
        for (int i = 1; i <= MyActivity.group.size(); i++) {
            arr[i] = MyActivity.group.get(i - 1).getName();
        }

        ArrayAdapter adapter = new ArrayAdapter(getActivity(), R.layout.spinner, arr);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                RelativeLayout r = (RelativeLayout) getActivity().findViewById(R.id.relativeLayout);
                if (position == 0) {
                    r.setEnabled(false);
                    r.requestDisallowInterceptTouchEvent(false);
                    setData();
                    new GroupPeople_Loader(getActivity(), group);
                } else {
                    r.setEnabled(true);
                    r.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Spinner s = (Spinner) getActivity().findViewById(R.id.groupSpinner);
                            Intent in = new Intent(getActivity(), Group_Update.class);
                            in.putExtra("name", s.getSelectedItem().toString());

                            startActivity(in);
                        }
                    });

                    setData(group.get(position - 1).getId());
                    new GroupPeople_Loader(getActivity(), getGroup_Data1(group.get(position - 1).getId()), group);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                setData();
                new GroupPeople_Loader(getActivity(), group);

            }

        });
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

    public void setData() {
        double budget = 0.0, onList = 0.0;
        for (int i = 0; i < group.size(); i++) {
            budget = budget + Double.parseDouble(group.get(i).getBudget());
        }
        for (int j = 0; j < person.size(); j++) {

            onList = onList + Double.parseDouble(person.get(j).getBudget());
        }
        budgetPeople.setText(String.valueOf(budget));
        spentPeople.setText(String.valueOf(onList));
        remainPeople.setText(String.valueOf(budget - onList));
    }

    public void setData(String id) {
        double budget = 0.0, onList = 0.0;
        for (int i = 0; i < group.size(); i++) {
            if (id.equals(group.get(i).getId())) {
                budget = Double.parseDouble(group.get(i).getBudget());
            }
        }
        for (int j = 0; j < person.size(); j++) {
            if (id.equals(person.get(j).getGroup())) {
                onList = onList + Double.parseDouble(person.get(j).getBudget());
            }
        }
        budgetPeople.setText(String.valueOf(budget));
        spentPeople.setText(String.valueOf(onList));
        remainPeople.setText(String.valueOf(budget - onList));
    }

    public String getGroup(String name) {
        String id = "";
        for (int i = 0; i < group.size(); i++) {
            if (name.equals(group.get(i).getName())) {
                id = group.get(i).getId();
                break;
            }
        }
        return id;
    }
}
