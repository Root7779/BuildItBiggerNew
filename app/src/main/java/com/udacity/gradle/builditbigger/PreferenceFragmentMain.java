package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

public class PreferenceFragmentMain extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.app_settings);
    }
}
