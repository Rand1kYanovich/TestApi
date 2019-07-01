package com.example.testapi;


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

import com.example.testapi.api.GetTasksResponse;
import com.example.testapi.api.NetworkService;
import com.example.testapi.util.FragmentUtil;
import com.example.testapi.util.SharedUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {
    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private LinearLayoutManager layoutManager;
    private Spinner spinner;
    private List<GetTasksResponse.Item> requestList;
    private List<GetTasksResponse.Item> filterList;


    private OnItemClickListener onItemClickListener;
    public MainFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        requestList = new ArrayList<>();
        filterList = new ArrayList<>();
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
        dataAdapter = new DataAdapter(getContext(), onItemClickListener);
        NetworkService.getInstance()
                .getJSONApi()
                .getList()
                .enqueue(new Callback<GetTasksResponse>() {
                    @Override
                    public void onResponse(Call<GetTasksResponse> call, Response<GetTasksResponse> response) {

                        if (response.isSuccessful()) {

                            requestList = new ArrayList<GetTasksResponse.Item>(response.body().getData());
                            Collections.sort(requestList, new Comparator<GetTasksResponse.Item>() {
                                @Override
                                public int compare(GetTasksResponse.Item o1, GetTasksResponse.Item o2) {
                                    return  (o1.getActualTime()+"").compareTo(o2.getActualTime()+"");
                                }
                            });
                            for (int i = 0;i<requestList.size();i++) {
                                if (requestList.get(i).getStatus().equals("open")) {
                                    requestList.get(i).setStatus("Открытые");
                                } else if (requestList.get(i).getStatus().equals("closed")) {
                                    requestList.get(i).setStatus("Закрытые");
                                } else if (requestList.get(i).getStatus().equals("in_progress")) {
                                    requestList.get(i).setStatus("В процессе");
                                }
                            }
                            dataAdapter = new DataAdapter(getContext(), onItemClickListener);
                            for(int i =0;i<requestList.size();i++){
                                filterList.add(requestList.get(i));
                            }
                            dataAdapter.setRequestList(requestList,filterList);
                            dataAdapter.notifyDataSetChanged();
                            recyclerView.setAdapter(dataAdapter);


                        }
                    }


                    @Override
                    public void onFailure(Call<GetTasksResponse> call, Throwable t) {

                    }
                });
        Log.e("Filter",SharedUtil.getFilter());

        recyclerView.setAdapter(dataAdapter);





        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(dataAdapter!=null) {
                    String[] choose = getResources().getStringArray(R.array.filterlist);
                    SharedUtil.setFilter(choose[position]);
                    dataAdapter.setData();
                }

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




}
