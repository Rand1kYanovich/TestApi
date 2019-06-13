package com.example.testapi;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.testapi.api.Get;
import com.example.testapi.api.NetworkService;
import com.example.testapi.util.FragmentUtil;

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


    private OnItemClickListener onItemClickListener;
    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);




        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                FullFragment fullFragment = new FullFragment("142");
                FragmentUtil.replace(getContext(),R.id.content,fullFragment);
            }
        };

        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapter(getDataSetRequest(),getContext(),onItemClickListener);
        recyclerView.setAdapter(dataAdapter);
        return rootView;
    }

    private ArrayList<Get.Item> resultsRequest = new ArrayList<Get.Item>();
    private List<Get.Item> getDataSetRequest() {
        return resultsRequest;
    }








    }
