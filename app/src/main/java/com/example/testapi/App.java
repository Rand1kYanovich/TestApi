package com.example.testapi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;




public class App extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;


    }

    public static Application getApplication() {
        return sApplication;
    }

    public static Context getContext() {
        return getApplication().getApplicationContext();
    }






}