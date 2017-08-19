package com.example.Gifts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;

import java.util.ArrayList;

/**
 * Created by Anosh on 11/26/2014.
 */
public class AddGift extends Activity {
    String personId;
    ArrayList<Store_Class> store = MyActivity.getStore();
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    ArrayList<Group_Class> group = MyActivity.getGroup();
    ArrayList<Person_Class> person = MyActivity.getPerson();
    Integer[] imageId = MyActivity.getImageId();
    TextView giftName;
    TextView giftPrice;
    Spinner giftStore;
    Spinner giftStatus;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        personId = getIntent().getExtras().getString("personId");
        String image;
        setContentView(R.layout.add_gift);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        giftName = (TextView) findViewById(R.id.gift_namea_g);
        giftPrice = (TextView) findViewById(R.id.giftPricea_g);
        giftStore = (Spinner) findViewById(R.id.storea_g);
        TextView name = (TextView) findViewById(R.id.personNamep_v);
        giftStatus = (Spinner) findViewById(R.id.gift_statusa_g);
        name.setText(getIntent().getExtras().getString("name"));
        loadSpinner();
        loadSpinner1();
        loadSpinner2();
        ImageView personImage = (ImageView) findViewById(R.id.imageView4);
        if (!getContact(personId).equals("null")) {
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(getContact(personId)));
            Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

            if (photoUri != null) {
                personImage.setImageURI(photoUri);
            }
        } else if (!(image = getPersonImage(personId)).equals("")) {
            if (image.substring(0, 21).equals("content://com.android")) {
                String[] photo_split = image.split("%3A");
                image = "content://media/external/images/media/" + photo_split[1];
            }

            personImage.setImageURI(Uri.parse(image));
        } else {
            personImage.setImageResource(imageId[0]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.done, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {    // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_accept:
                String price = giftPrice.getText().toString();
                if (giftName.getText().toString().equals("")) {
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("You did not enter a gift name.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else if (giftPrice.getText().toString().equals("")) {
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage("You did not enter a price.")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    if (notEnough(price) == false) {
                        MyActivity.setGift(giftName.getText().toString(), price, personId, giftStatus.getSelectedItem().toString(), "", getID(giftStore.getSelectedItem().toString()));

                        onBackPressed();

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
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean notEnough(String budget) {
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
        giftsBudget = giftsBudget + Double.parseDouble(budget);
        if (giftsBudget <= personBudget) {
            notEnough = false;
        }
        return notEnough;
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

    public String getPersonImage(String id) {
        String name = null;
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getId())) {
                name = person.get(i).getImage();
                break;
            }
        }
        return name;
    }

    public void loadSpinner() {
        String arr[] = new String[store.size()];
        for (int i = 0; i < store.size(); i++) {
            arr[i] = store.get(i).getName();
        }

        Spinner s = (Spinner) findViewById(R.id.storea_g);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner, arr);
        s.setAdapter(adapter);
    }

    public void loadSpinner1() {
        ArrayList<String> arr = new ArrayList<String>();
        arr.add("<No Selection>");
        for (int i = 1; i <= gift.size(); i++) {
            outter:
            {
                for (int j = 0; j < arr.size(); j++) {
                    if (arr.get(j).equals(gift.get(i - 1).getName())) {
                        break outter;
                    }
                }
                arr.add(gift.get(i - 1).getName());

            }
        }

        final Spinner s = (Spinner) findViewById(R.id.gifts);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner, arr);
        s.setAdapter(adapter);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (s.getSelectedItem().equals("<No Selection>")) {
                    giftName.setText("");
                    giftPrice.setText("");
                    giftStore.setSelection(0);
                } else {
                    getData((String) s.getSelectedItem());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                giftName.setText("");
                giftPrice.setText("");
                giftStore.setSelection(0);
            }
        });
    }

    public void getData(String name) {

        Spinner giftStore = (Spinner) findViewById(R.id.storea_g);

        for (int i = 0; i < gift.size(); i++) {
            if (name.equals(gift.get(i).getName())) {
                giftName.setText(gift.get(i).getName());
                giftPrice.setText(gift.get(i).getAmount());
                for (int j = 0; j < store.size(); j++) {
                    if (gift.get(i).getStore().equals(store.get(j).getId())) {
                        giftStore.setSelection(j);
                        break;
                    }
                }
                break;
            }
        }
    }

    public void loadSpinner2() {
        String arr[] = {"Not purchased", "Purchased", "Shipped", "Ordered", "Wrapped"};

        Spinner s = (Spinner) findViewById(R.id.gift_statusa_g);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.spinner, arr);
        s.setAdapter(adapter);
    }

    public String getContact(String id) {
        String name = "";
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getId())) {
                name = person.get(i).getContact();
                break;
            }
        }
        return name;
    }
}