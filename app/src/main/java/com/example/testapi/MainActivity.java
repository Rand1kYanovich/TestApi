package com.example.testapi;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.example.testapi.util.FragmentUtil;
import com.example.testapi.util.SharedUtil;

import static com.example.testapi.App.getContext;

public class MainActivity extends AppCompatActivity {

SharedPreferences sPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sPref = getPreferences(MODE_PRIVATE);
        SharedUtil.initPref(sPref);
        setContentView(R.layout.activity_main);
        MainFragmentReplace();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void MainFragmentReplace(){
        MainFragment mainFragment = new MainFragment();
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.content, mainFragment)
                .commit();
    }

}

