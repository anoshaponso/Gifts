package com.example.Gifts;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Spinner;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.Tabs.People;
import com.example.Gifts.Tabs.Progress;
import com.example.Gifts.Tabs.Store;
import com.example.Gifts.Tabs.TabListener;
import com.example.Gifts.XML.ReadXMLFile;
import com.example.Gifts.XML.WriteXMLFile;

import java.util.ArrayList;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    public static ArrayList<Person_Class> person = new ArrayList<Person_Class>();
    public static ArrayList<Gift_Class> gift = new ArrayList<Gift_Class>();
    public static ArrayList<Group_Class> group = new ArrayList<Group_Class>();
    public static ArrayList<Store_Class> store = new ArrayList<Store_Class>();
    static Integer[] imageId = {
            R.drawable.images1,
            R.drawable.images2,
            R.drawable.images3,
            R.drawable.images4,
            R.drawable.images
    };
    ActionBar.Tab tab1, tab2, tab3;
    Fragment fragmentTab1 = new People();
    Fragment fragmentTab2 = new Store();
    Fragment fragmentTab3 = new Progress();

    public static ArrayList<Store_Class> getStore() {
        return store;
    }

    public static void setStore(String name) {
        if (store.size() != 0) {
            store.add(new Store_Class(String.valueOf((Integer.parseInt(store.get(store.size() - 1).getId())) + 1), name));
            new WriteXMLFile(person, gift, group, store);
        } else {
            store.add(new Store_Class("1", name));
            new WriteXMLFile(person, gift, group, store);
        }
    }

    public static ArrayList<Person_Class> getPerson() {
        return person;
    }

    public static void setPerson(String name, String image, String budget, String group1, String contact) {
        if (person.size() != 0) {
            person.add(new Person_Class(String.valueOf((Integer.parseInt(person.get(person.size() - 1).getId())) + 1), name, image, budget, group1, contact));
            new WriteXMLFile(person, gift, group, store);
        } else {
            person.add(new Person_Class("1", name, image, budget, group1, contact));
            new WriteXMLFile(person, gift, group, store);
        }
    }

    public static ArrayList<Gift_Class> getGift() {
        return gift;
    }

    public static void setGift(String name, String amount, String personName, String status, String image, String store1) {
        if (gift.size() != 0) {
            gift.add(new Gift_Class(String.valueOf((Integer.parseInt(gift.get(gift.size() - 1).getId())) + 1), name, amount, personName, status, image, store1));
            new WriteXMLFile(person, gift, group, store);
        } else {
            gift.add(new Gift_Class("1", name, amount, personName, status, image, store1));
            new WriteXMLFile(person, gift, group, store);
        }
    }

    public static ArrayList<Group_Class> getGroup() {
        return group;
    }

    public static void setGroup(String name, String image, String budget) {
        if (group.size() != 0) {
            group.add(new Group_Class(String.valueOf((Integer.parseInt(group.get(group.size() - 1).getId())) + 1), name, image, budget));
            new WriteXMLFile(person, gift, group, store);
        } else {
            group.add(new Group_Class("1", name, image, budget));
            new WriteXMLFile(person, gift, group, store);
        }
    }

    public static Integer[] getImageId() {
        return imageId;
    }

    public void setImageId(Integer[] imageId) {
        MyActivity.imageId = imageId;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        new ReadXMLFile(this);
        if (store.size() == 0) {
            addStores();
        }
        if (group.size() == 0) {
            addGroup();
        }
        ActionBar actionBar = getActionBar();

        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        tab1 = actionBar.newTab().setText("People");
        tab2 = actionBar.newTab().setText("Store");
        tab3 = actionBar.newTab().setText("Progress");

        tab1.setTabListener(new TabListener(fragmentTab1));
        tab2.setTabListener(new TabListener(fragmentTab2));
        tab3.setTabListener(new TabListener(fragmentTab3));

        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
        actionBar.addTab(tab3);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.recreate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_addPerson:
                if (group.size() != 0) {
                    Spinner s = (Spinner) findViewById(R.id.groupSpinner);
                    Intent pers = new Intent(this, AddPerson.class);
                    if (s != null) {
                        pers.putExtra("group", "" + s.getSelectedItemId());
                    }
                    startActivity(pers);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("No Groups")
                            .setMessage("You should add group First.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                return true;

            case R.id.action_addGroup:
                Intent gro = new Intent(this, AddGroup.class);
                startActivity(gro);
                return true;

            case R.id.action_addStore:
                Intent sto = new Intent(this, AddStore.class);
                startActivity(sto);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        new WriteXMLFile(person, gift, group, store);
    }

    //    public void onGroupEdit(View v){
//        Spinner s= (Spinner) findViewById(R.id.groupSpinner);
//        Intent in=new Intent(this,Group_Update.class);
//        in.putExtra("name",s.getSelectedItem().toString());
//
//        startActivity(in);
//    }
    public void addStores() {
        setStore("Amazon");
        setStore("eBay");
        setStore("Asos");
        setStore("Walmart");
        setStore("Etsy");
        setStore("MR PORTER");
        setStore("Alibaba.com");
        setStore("NASTY GAL");
        setStore("Zappos");
        setStore("ModCloth");
    }

    public void addGroup() {
        setGroup("Friends", "", "0.00");
        setPerson("Friend1", "", "0.0", "1", "");
    }
//http://androidexample.com/Tab_Layout_%7C_TabBar_-_Android_Example/index.php?view=article_discription&aid=103&aaid=125
//http://www.androidbegin.com/tutorial/implementing-fragment-tabs-in-android/
}
