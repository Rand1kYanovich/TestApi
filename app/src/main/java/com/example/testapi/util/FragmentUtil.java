package com.example.testapi.util;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.example.testapi.MainActivity;
import com.example.testapi.R;


public class FragmentUtil {



    public static void replace(Context context, int layout, Fragment fragment, boolean backStack){
        ((MainActivity)context).getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(layout, fragment)
                .addToBackStack(null).commit();

    }


}
