package com.example.Gifts.Tabs;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Gifts.R;
import com.example.Gifts.Store_Tab.Store_Loader;

/**
 * Created by adminslt on 11/25/2014.
 */
public class Store extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.store, container, false);
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Store_Loader(getActivity());


    }
}
