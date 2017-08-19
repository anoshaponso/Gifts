package com.example.Gifts;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * Created by Anosh on 12/17/2014.
 */
public class Gallery extends Activity {
    private static final int SELECT_PICTURE = 1;
    String name;
    String budget;
    int id;
    private String selectedImagePath;
    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        name = getIntent().getExtras().getString("name");
        budget = getIntent().getExtras().getString("budget");
        id = getIntent().getExtras().getInt("id");
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri selectedImageUri = data.getData();
                if (Build.VERSION.SDK_INT < 19) {
                    selectedImagePath = getPath(selectedImageUri);
                    Intent in = new Intent(this, AddPerson1.class);
                    in.putExtra("image", String.valueOf(selectedImagePath));
                    in.putExtra("name", name);
                    in.putExtra("budget", budget);
                    in.putExtra("id", id);
                    startActivity(in);

                } else {

                    Intent in = new Intent(this, AddPerson1.class);
                    in.putExtra("image", String.valueOf(selectedImageUri));
                    in.putExtra("name", name);
                    in.putExtra("budget", budget);
                    in.putExtra("id", id);
                    startActivity(in);

                }
            }
        }
    }

    /**
     * helper to retrieve the path of an image URI
     */
    public String getPath(Uri uri) {
        if (uri == null) {
            return null;
        }
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }

}