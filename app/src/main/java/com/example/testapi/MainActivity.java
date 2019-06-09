package com.example.testapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DataAdapter dataAdapter;
    private LinearLayoutManager layoutManager;


    private OnItemClickListener onItemClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onItemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();
            }
        };

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        dataAdapter = new DataAdapter(getDataSetMatches(),getApplicationContext(),onItemClickListener);
        recyclerView.setAdapter(dataAdapter);



    }

    private ArrayList<Request> resultsRequest = new ArrayList<Request>();
    private List<Request> getDataSetMatches() {
        return resultsRequest;
    }
}

