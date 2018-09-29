package com.udacity.gradle.builditbigger;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPrefsUtils.setThemeAsUser(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if(SharedPrefsUtils.getFirstTimeForFile(this,"ABOUT_US")){
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setTitle(R.string.about_us_dialog_title)
                    .setMessage(R.string.about_us_dialog_txt)
                    .setPositiveButton(getString(R.string.about_us_dialog_positive_btn), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            SharedPrefsUtils.setFirstTimeFalseForFile(AboutUsActivity.this,"ABOUT_US");
                        }
                    })
            .show();
        }
    }


}
