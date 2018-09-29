package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.udacity.gradle.builditbigger.Consts.DEFAULT_FIRST_TIME;
import static com.udacity.gradle.builditbigger.Consts.KEY_FIRST_TIME;

class SharedPrefsUtils {

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

    //Function to set the theme of the activities based on the preference
    public static void setThemeAsUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if(preferences.getBoolean(context.getString(R.string.key_dark_enabled), false)){
            context.setTheme(R.style.AppTheme_Dark);
        }else{
            context.setTheme(R.style.AppTheme);
        }
    }

}
