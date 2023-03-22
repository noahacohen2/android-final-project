package com.example.finalproject;

import android.app.Application;
import android.content.Context;

public class FinalProjectApplication extends Application {
    static private Context context;
    public static Context getMyContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
