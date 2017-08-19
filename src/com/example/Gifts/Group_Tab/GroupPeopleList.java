package com.example.Gifts.Group_Tab;

import android.app.Activity;
import android.content.ContentUris;
import android.net.Uri;
import android.os.Handler;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.Gifts.Classes.Gift_Class;
import com.example.Gifts.Classes.Person_Class;
import com.example.Gifts.MyActivity;
import com.example.Gifts.R;

import java.util.ArrayList;

/**
 * Created by Anosh on 11/11/2014.
 */
public class GroupPeopleList extends ArrayAdapter<String> {
    private static final int PROGRESS = 0x1;
    private final Activity context;
    Integer[] imageId = MyActivity.getImageId();
    ArrayList<String> personIds;
    ArrayList<Person_Class> person;
    ArrayList<Gift_Class> gift = MyActivity.getGift();
    View rowView;
    private ProgressBar mProgress;
    private double mProgressStatus = 0.0;
    private Handler mHandler = new Handler();

    public GroupPeopleList(Activity context, ArrayList<String> personIds, ArrayList<Person_Class> person) {
        super(context, R.layout.person_view, personIds);

        this.context = context;
        this.personIds = personIds;
        this.person = person;

    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        Uri photoUri = null;
        String image;
        if (!getPersonContact(personIds.get(position)).equals("null")) {
            Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(getPersonContact(personIds.get(position))));
            photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        }
        LayoutInflater inflater = context.getLayoutInflater();

        rowView = inflater.inflate(R.layout.person_view, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.personNamep_v1);
        txtTitle.setText(getPersonName(personIds.get(position)));

        TextView txtTitle1 = (TextView) rowView.findViewById(R.id.budgetp_v);
        txtTitle1.setText(getPersonBudget(personIds.get(position)));
        ImageView img = (ImageView) rowView.findViewById(R.id.personImage);
        if (photoUri != null) {
            img.setImageURI(photoUri);

        } else if (!(image = person.get(position).getImage()).equals("")) {
            if (image.substring(0, 21).equals("content://com.android")) {
                String[] photo_split = image.split("%3A");
                image = "content://media/external/images/media/" + photo_split[1];
            }

            img.setImageURI(Uri.parse(image));
        } else {
            img.setImageResource(imageId[0]);
        }
        final TextView spent = (TextView) rowView.findViewById(R.id.spentp_v);
        spent.setText(String.valueOf(spent(personIds.get(position))));
        mProgress = (ProgressBar) rowView.findViewById(R.id.progressBar);
        mProgressStatus = 0.0;
        final double total = Double.parseDouble(getPersonBudget(personIds.get(position)));
        final double spentAmount = Double.parseDouble(String.valueOf(spent(personIds.get(position))));

        new Thread(new Runnable() {
            public void run() {
                if (mProgressStatus < 100) {
                    mProgressStatus = spentAmount * 100 / total;                     // Update the progress bar
                    mHandler.post(new Runnable() {
                        public void run() {
                            mProgress.setProgress(Integer.valueOf((int) mProgressStatus));
                        }
                    });
                }
            }
        }).start();
        return rowView;
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

    public String getPersonContact(String id) {
        String photo = "";
        for (int i = 0; i < person.size(); i++) {
            if (id.equals(person.get(i).getId())) {
                photo = (person.get(i).getContact());
                break;
            }
        }
        return photo;
    }
}