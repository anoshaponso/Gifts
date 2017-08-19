package com.example.Gifts.Images;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.Gifts.R;

/**
 * Created by Anosh on 11/11/2014.
 */
public class ImageList extends ArrayAdapter<Integer> {
    private final Activity context;


    Integer[] imageId;


    public ImageList(Activity context, Integer[] imageId) {
        super(context, R.layout.image_view, imageId);

        this.context = context;
        this.imageId = imageId;


    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.image_view, null, true);
        ImageView image = (ImageView) rowView.findViewById(R.id.imageView);

        image.setImageResource(imageId[position]);


        return rowView;
    }

}