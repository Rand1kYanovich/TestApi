package com.example.testapi.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.testapi.App;
import com.example.testapi.MainActivity;
import com.example.testapi.R;

public class SharedUtil {
    private static SharedPreferences sPref;

    public static void initPref(SharedPreferences sharedPreferences){
        sPref = sharedPreferences;
    }


    public static SharedPreferences.Editor getEditor() {
        return sPref.edit();
    }

    public static String getFilter(){
        Log.e("SPREF",sPref.getString("Filter","Все"));
        return sPref.getString("Filter","Все");
    }

    public static void setFilter(String filter){
        Log.e("SPREF_SET",sPref.getString("Filter","Все"));
        getEditor().putString("Filter",filter).commit();
    }


}
