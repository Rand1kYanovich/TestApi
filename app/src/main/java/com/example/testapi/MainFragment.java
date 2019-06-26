package com.example.testapi;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.testapi.api.Get;
import com.example.testapi.api.NetworkService;
import com.example.testapi.util.FragmentUtil;
import com.example.testapi.util.SharedUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private LinearLayoutManager layoutManager;
    private Spinner spinner;



    private OnItemClickListener onItemClickListener;
    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        setHasOptionsMenu(true);

        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view, int position,int id) {
                FullFragment fullFragment = new FullFragment(id+"",getContext());
                FragmentUtil.replace(getContext(),R.id.content,fullFragment);
            }
        };
        spinner = (Spinner)rootView.findViewById(R.id.spinner);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapter(getDataSetRequest(),getContext(),onItemClickListener);
        recyclerView.setAdapter(dataAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String[] choose = getResources().getStringArray(R.array.filterlist);
                        Toast.makeText(getContext(), choose[position], Toast.LENGTH_SHORT).show();
                        SharedUtil.setFilter(choose[position]);
                        dataAdapter.setData();

                }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return true;

    }

    private ArrayList<Get.Item> resultsRequest = new ArrayList<Get.Item>();
    private List<Get.Item> getDataSetRequest() {
        return resultsRequest;
    }








    }
