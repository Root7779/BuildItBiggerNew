package com.udacity.gradle.builditbigger;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener{

    private SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        mPrefs.registerOnSharedPreferenceChangeListener(this);
        SharedPrefsUtils.setThemeAsUser(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_settings,new PreferenceFragmentMain())
                .commit();
        if(getSupportActionBar()!= null){getSupportActionBar().setDisplayHomeAsUpEnabled(true);}
    }

    //To Unregister listener
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    //to Show changes done by user
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        this.recreate();
    }
}
