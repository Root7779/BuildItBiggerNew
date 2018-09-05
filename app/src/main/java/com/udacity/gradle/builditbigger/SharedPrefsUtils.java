package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPrefsUtils {

    private static final String KEY_FIRST_TIME = "FirstTimeUsing";
    private static final boolean DEFAULT_FIRST_TIME = true;

    public static boolean getFirstTimeForFile(Context context,String fname){
        SharedPreferences prefs = context.getSharedPreferences(fname,Context.MODE_PRIVATE);
        if(prefs.getBoolean(KEY_FIRST_TIME,DEFAULT_FIRST_TIME) == DEFAULT_FIRST_TIME){
            return true;
        }
        return false;
    }

    public static void setFirstTimeFalseForFile(Context context,String fname){
        SharedPreferences prefs = context.getSharedPreferences(fname,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_FIRST_TIME,false);
        editor.apply();
    }

}
