package com.example.testapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapi.util.FragmentUtil;

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
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.content, mainFragment)
                .addToBackStack(null).commit();

    }


}

