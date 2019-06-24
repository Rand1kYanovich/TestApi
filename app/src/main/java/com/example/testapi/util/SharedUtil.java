package com.example.testapi.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testapi.App;
import com.example.testapi.MainActivity;

public class SharedUtil {

    private static SharedPreferences sPref;
    private static final String SHARED_PREF_NAME = "filterPref";


    public static void init(){
        sPref = App.getContext().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public static SharedPreferences.Editor getEditor() {
        return sPref.edit();
    }

    public static String getFilter(){
        return sPref.getString("Filter","all");
    }

    public static void setFilter(String filter){
        getEditor().putString("Filter",filter).commit();
    }
}
