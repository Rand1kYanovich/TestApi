package com.example.testapi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class FullFragment extends Fragment {
    private TextView title,date,location,status,description,specialist;
    private String id;


    public  FullFragment(String id) {
        this.id = id;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_full,container,false);


        return rootView;
    }


}
