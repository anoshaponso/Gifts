package com.example.Gifts.Progress_Tab;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;


/**
 * Created by Anosh on 11/11/2014.
 */
public class ProgressGift_Loader extends View {

    public static Activity context;
    ListView list;
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<Gift_Class> notPurchest;
    String status;

    public ProgressGift_Loader(Context context, String status) {
        super(context);
        ProgressGift_Loader.context = (Activity) context;
        this.status = status;
        TextView txt = (TextView) (ProgressGift_Loader.context).findViewById(R.id.textView);
        txt.setText(this.status);
        notPurchest();

    }

    public ArrayList<Gift_Class> getNot(String id) {
        notPurchest = new ArrayList<Gift_Class>();
        for (int i = 0; i < gift.size(); i++) {
            if (gift.get(i).getStatus().equals(id)) {
                notPurchest.add(gift.get(i));
            }
        }
        return notPurchest;
    }

    public void notPurchest() {

        NotPurchasedGiftList adapter = new NotPurchasedGiftList(context, getNot(status));
        list = (ListView) context.findViewById(R.id.progresslistView);
        list.setAdapter(adapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "You Clicked at " + (notPurchest.get(position).getName()), Toast.LENGTH_SHORT).show();

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

}