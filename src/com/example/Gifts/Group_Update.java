package com.example.Gifts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.XML.WriteXMLFile;

import java.util.ArrayList;

/**
 * Created by Anosh on 12/11/2014.
 */
public class Group_Update extends Activity {
    ArrayList<Group_Class> group = MyActivity.getGroup();
    ArrayList<Store_Class> store = MyActivity.getStore();
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<Person_Class> person = MyActivity.getPerson();
    Activity context = this;
    String id;

    TextView groupName;
    TextView groupBudget;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_update);
        groupName = (TextView) findViewById(R.id.groupNamea_gu);
        groupBudget = (TextView) findViewById(R.id.groupBudgeta_gu);
        String groupName1 = getIntent().getExtras().getString("name");
        groupName.setText(groupName1);
        id = getGroup(groupName1);
        setBudget(id);

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
                groupBudget = (TextView) findViewById(R.id.groupBudgeta_gu);
                String budget = groupBudget.getText().toString();
                if (notEnough(id, budget) == false) {
                    int index = 0;
                    for (int i = 0; i < group.size(); i++) {
                        if (id.equals(group.get(i).getId())) {
                            index = group.indexOf(group.get(i));
                        }
                    }

                    group.get(index).setBudget(budget);
                    new WriteXMLFile(person, gift, group, store);
                    Intent pers = new Intent(this, MyActivity.class);
                    startActivity(pers);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Not Enough Budget")
                            .setMessage("Your Budget is not enough.")
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
                        .setTitle("Delete group")
                        .setMessage("Are you sure you want to delete this group?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < group.size(); i++) {
                                    if (id.equals(group.get(i).getId())) {
                                        removePerson(group.get(i).getId());
                                        group.remove(group.get(i));
                                        break;
                                    }
                                }
                                new WriteXMLFile(person, gift, group, store);
                                Intent main = new Intent(context, MyActivity.class);
                                startActivity(main);
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

    public void removePerson(String id) {
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getGroup())) {
                person.remove(i);
            }
        }
    }

    public String getGroup(String name) {
        String id = null;
        for (int i = 0; i < group.size(); i++) {
            if (name.equals(group.get(i).getName())) {
                id = group.get(i).getId();
                break;
            }
        }
        return id;
    }

    public void setBudget(String id) {
        for (int i = 0; i < group.size(); i++) {
            if (id.equals(group.get(i).getId())) {
                groupBudget.setText(group.get(i).getBudget());

            }
        }

    }

    public boolean notEnough(String id, String budget) {
        boolean notEnough = true;
        double groupBudget = 0.0;
        for (int i = 0; i < group.size(); i++) {
            if (id.equals(group.get(i).getId())) {
                groupBudget = Double.parseDouble(budget);
                break;
            }
        }
        double personsBudget = 0.0;
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getGroup())) {
                personsBudget = personsBudget + Double.parseDouble(person.get(i).getBudget());
            }
        }

        if (personsBudget <= groupBudget) {
            notEnough = false;
        }
        return notEnough;
    }
}