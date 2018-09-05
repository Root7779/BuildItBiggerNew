package com.udacity.gradle.builditbigger;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        if(SharedPrefsUtils.getFirstTimeForFile(this,"ABOUT_US")){
            new AlertDialog.Builder(this)
                    .setCancelable(true)
                    .setTitle("Got You!")
                    .setMessage("HA HA HA ...\nIt is U.S. Not Us!\nGot You! Just joking Here After all this is joking App!\n*Don\'t Take it seriously")
                    .setPositiveButton("OK You Got Me", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
            .show();
 //           SharedPrefsUtils.setFirstTimeFalseForFile(this,"ABOUT_US");
 //       }
    }
}
