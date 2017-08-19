package com.example.Gifts.Gift;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Gift_List_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.R;

import java.util.ArrayList;

/**
 * Created by Anosh on 11/11/2014.
 */
public class GiftList extends ArrayAdapter<Gift_Class> {
    private final Activity context;


    ArrayList<Person_Class> person;
    ArrayList<Gift_Class> giftlist;
    ArrayList<Gift_List_Class> not;
    ArrayList<Gift_List_Class> purchased;
    ArrayList<Gift_List_Class> shipped;
    ArrayList<Gift_List_Class> ordered;
    ArrayList<Gift_List_Class> wrapped;


    public GiftList(Activity context, ArrayList<Person_Class> person, ArrayList<Gift_Class> giftlist) {
        super(context, R.layout.gift_view, giftlist);
        this.person = person;
        this.context = context;
        this.giftlist = giftlist;
        getList();
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.gift_view, null, true);
        TextView giftName = (TextView) rowView.findViewById(R.id.giftNameg_v);
        TextView giftPrice = (TextView) rowView.findViewById(R.id.budgetg_v);
        TextView giftStatus = (TextView) rowView.findViewById(R.id.giftStatusg_v);

        giftName.setText(giftlist.get(position).getName());
        giftPrice.setText(giftlist.get(position).getAmount());
        giftStatus.setText(giftlist.get(position).getStatus());

        ListView ls = (ListView) rowView.findViewById(R.id.groupListView);

        return rowView;
    }

    public void getList() {
        not = new ArrayList<Gift_List_Class>();
        for (int i = 0; i < giftlist.size(); i++) {
            if (giftlist.get(i).getStatus().equals("not")) {
                not.add(new Gift_List_Class(giftlist.get(i).getId(), giftlist.get(i).getName(), giftlist.get(i).getAmount(), giftlist.get(i).getStatus(), giftlist.get(i).getImage(), giftlist.get(i).getStore()));
            }
        }
    }


}