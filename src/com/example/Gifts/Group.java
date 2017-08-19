package com.example.Gifts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Group_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.Classes.Store_Class;
import com.example.Gifts.Gift.Gift_Loader;
import com.example.Gifts.XML.WriteXMLFile;

import java.util.ArrayList;

/**
 * Created by Anosh on 11/26/2014.
 */
public class Group extends Activity {
    String name;
    String budget;
    String personId;
    ArrayList<Person_Class> person = MyActivity.getPerson();
    ArrayList<Store_Class> store = MyActivity.getStore();
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    String image;
    Activity context = this;
    ArrayList<Group_Class> group = MyActivity.getGroup();
    Activity a = (Activity) this;
    Integer[] imageId = MyActivity.getImageId();
    TextView spent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name = getIntent().getExtras().getString("name");
        budget = getIntent().getExtras().getString("budget");
        personId = getIntent().getExtras().getString("id");
        setContentView(R.layout.group);
        ImageView personImage = (ImageView) findViewById(R.id.personImageg);
        if (!getContact(personId).equals("null")) {
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(getContact(personId)));
            Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);

            if (photoUri != null) {
                personImage.setImageURI(photoUri);
            } else if (!(image = getPersonImage(personId)).equals("")) {
                if (image.substring(0, 21).equals("content://com.android")) {
                    String[] photo_split = image.split("%3A");
                    image = "content://media/external/images/media/" + photo_split[1];
                }

                personImage.setImageURI(Uri.parse(image));
            } else {
                personImage.setImageResource(imageId[0]);
            }

            TextView personName = (TextView) findViewById(R.id.personNameg);
            EditText budget1 = (EditText) findViewById(R.id.budgetg);

            personName.setText(name);
            budget1.setText(budget);
            new Gift_Loader(this, personId);
            spent = (TextView) findViewById(R.id.spentAmountgr);
            spent.setText("" + spent(personId));

        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        recreate();
    }

//    @Override
//    public void onResume() {
//        super.onResume();
//        new Gift_Loader(this, personId);
//        spent.setText("" + spent(personId));
//    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {    // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.add:
                if (store.size() != 0) {
                    Intent addGift = new Intent(this, AddGift.class);
                    addGift.putExtra("personId", personId);
                    addGift.putExtra("name", name);

                    startActivity(addGift);
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("No Store")
                            .setMessage("You should add store First.")
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
                        .setMessage("Are you sure you want to delete this person?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < person.size(); i++) {
                                    if (personId.equals(person.get(i).getId())) {
                                        person.remove(person.get(i));
                                        break;
                                    }
                                }
                                new WriteXMLFile(person, gift, group, store);
                                Intent in = new Intent(a, MyActivity.class);
                                startActivity(in);
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            case R.id.done:
                int index = 0;
                for (int i = 0; i < person.size(); i++) {
                    if (personId.equals(person.get(i).getId())) {
                        index = person.indexOf(person.get(i));
                    }
                }
                EditText personBudget = (EditText) findViewById(R.id.budgetg);
                person.get(index).setBudget(personBudget.getText().toString());
                new WriteXMLFile(person, gift, group, store);
                recreate();

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public double spent(String id) {
        double total = 0;
        for (int i = 0; i < gift.size(); i++) {
            if (id.equals(gift.get(i).getPersonName())) {
                total = total + Double.parseDouble(gift.get(i).getAmount());
            }
        }
        return total;
    }

    public String getID(String name) {
        String id = null;
        for (int i = 0; i < group.size(); i++) {
            if (group.get(i).getName().equals(name)) {
                id = group.get(i).getId();
                break;
            }
        }
        return id;
    }
}