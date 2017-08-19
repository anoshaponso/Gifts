package com.example.Gifts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.XML.WriteXMLFile;

import java.util.ArrayList;

/**
 * Created by Anosh on 12/1/2014.
 */

public class Gift_Show extends Activity {
    ArrayList<Store_Class> store = MyActivity.getStore();
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<Group_Class> group = MyActivity.getGroup();
    ArrayList<Person_Class> person = MyActivity.getPerson();
    String giftId;
    String personId;
    String budget;
    Activity context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gift);
        String name = getIntent().getExtras().getString("name");
        budget = getIntent().getExtras().getString("budget");
        String store = getIntent().getExtras().getString("store");
        String status = getIntent().getExtras().getString("status");
        giftId = getIntent().getExtras().getString("id");
        personId = getIntent().getExtras().getString("personId");
        loadSpinner();
        loadSpinner1();

        TextView giftName = (TextView) findViewById(R.id.giftNameValuegi);
        EditText giftAmount = (EditText) findViewById(R.id.giftAmountValuegi);

        Spinner s = (Spinner) findViewById(R.id.store);
        Spinner s2 = (Spinner) findViewById(R.id.status);

        giftName.setText(name);
        giftAmount.setText(budget);
        if (!store.equals("")) {
            s.setSelection(Integer.parseInt((store)) - 1);
        }
        for (int i = 0; i < s2.getCount(); i++) {
            if (s2.getItemAtPosition(i).toString().equals(status)) {
                s2.setSelection(i);
            }
        }
    }

    public String getStore(String name) {
        String id = "";
        for (int i = 0; i < store.size(); i++) {
            if (name.equals(store.get(i).getName())) {
                id = store.get(i).getId();
                break;
            }
        }
        return id;
    }

    public void loadSpinner() {
        String arr[] = new String[store.size()];
        for (int i = 0; i < store.size(); i++) {
            arr[i] = store.get(i).getName();
        }
        Spinner s = (Spinner) findViewById(R.id.store);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner, arr);
        s.setAdapter(adapter);
    }

    public void loadSpinner1() {
        String arr[] = {"Not purchased", "Purchased", "Shipped", "Ordered", "Wrapped"};

        Spinner s = (Spinner) findViewById(R.id.status);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner, arr);
        s.setAdapter(adapter);
    }

    public boolean notEnough(String budget1) {
        boolean notEnough = true;
        double personBudget = 0.0;
        for (int i = 0; i < person.size(); i++) {
            if (personId.equals(person.get(i).getId())) {
                personBudget = Double.parseDouble(person.get(i).getBudget());
                break;
            }
        }
        double giftsBudget = 0.0;
        for (int i = 0; i < gift.size(); i++) {
            if (personId.equals(gift.get(i).getPersonName())) {
                giftsBudget = giftsBudget + Double.parseDouble(gift.get(i).getAmount());
            }
        }
        giftsBudget = giftsBudget + Double.parseDouble(budget1) - Double.parseDouble(budget);
        if (giftsBudget <= personBudget) {
            notEnough = false;
        }
        return notEnough;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.done:
                EditText giftPrice = (EditText) findViewById(R.id.giftAmountValuegi);
                if (!notEnough(giftPrice.getText().toString())) {
                    int index = 0;
                    for (int i = 0; i < gift.size(); i++) {
                        if (giftId.equals(gift.get(i).getId())) {
                            index = gift.indexOf(gift.get(i));
                            break;
                        }
                    }
                    TextView giftName = (TextView) findViewById(R.id.giftNameValuegi);


                    Spinner giftStore = (Spinner) findViewById(R.id.store);
                    Spinner giftStatus = (Spinner) findViewById(R.id.status);
                    gift.get(index).setAmount(giftPrice.getText().toString());
                    gift.get(index).setStore(getID(giftStore.getSelectedItem().toString()));
                    gift.get(index).setStatus(giftStatus.getSelectedItem().toString());
                    onBackPressed();
                    new WriteXMLFile(person, gift, group, store);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Not Enough Budget")
                            .setMessage("Your Budget is not enough to get this gift.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                return true;


            case R.id.delete:
                new AlertDialog.Builder(this)
                        .setTitle("Delete entry")
                        .setMessage("Are you sure you want to delete this gift?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < gift.size(); i++) {
                                    if (giftId.equals(gift.get(i).getId())) {
                                        gift.remove(gift.get(i));
                                        break;
                                    }
                                }
                                new WriteXMLFile(person, gift, group, store);
                                onBackPressed();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getID(String name) {
        String id = null;
        for (int i = 0; i < store.size(); i++) {
            if (store.get(i).getName().equals(name)) {
                id = store.get(i).getId();
                break;
            }
        }
        return id;
    }
}