package com.example.uygulama_sayac;

import android.content.Context;
import android.content.SharedPreferences;

public class SetupClass {
    Boolean ust_ses;
    Boolean alt_ses;
    Boolean ust_titresim;
    Boolean alt_titresim;

    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    static SetupClass setupClass = null;

    private SetupClass(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("setup", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SetupClass getInstance(Context context){
        if(setupClass==null)
            setupClass=new SetupClass(context);
        return setupClass;
    }
}
