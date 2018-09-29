package com.udacity.gradle.builditbigger;

/**
 * This is A free Version Source code for Showing ads and button to send data to Android Library
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.vyas.pranav.androidjokelib.JokeTellingActivityLib;
import com.vyas.pranav.javajokelibrary.Joker;

import static com.vyas.pranav.androidjokelib.JokeTellingActivityLib.JOKE_KEY;

public class MainActivityNew extends AppCompatActivity implements EndpointsAsyncTask.AsyncCallback,EndpointsAsyncTask.AsyncCallbackBegin{
    private static final String TAG = "MainActivityNewFree";
    private ProgressBar progress;
    private InterstitialAd mInterstitialAd;

    /**
     * showAds() - Showing Banner Ads in Free Version
     * initlizeInterAdd - Showing Intertial ads
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progressBarMain);
        showAds();
        initlizeInterAdd();
        showIntro();
    }

    //Showing Intro for First Time
    private void showIntro(){
        if(SharedPrefsUtils.getFirstTimeForFile(this,"FIRST_TIME")){
            new AlertDialog.Builder(this)
                    .setMessage(this.getString(R.string.main_free_dialog_detail))
                    .setTitle(getString(R.string.main_free_dialog_title))
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .setCancelable(false)
                    .show();
            SharedPrefsUtils.setFirstTimeFalseForFile(MainActivityNew.this,"FIRST_TIME");
        }
    }

    /**
     * joke - Joke String to be sent to EndPoints
     */
    public void tellJoke(View view) {
        Joker jokerJavaLib = new Joker();
        String joke = jokerJavaLib.getJoke();
        Log.d(TAG, "tellJoke: Joke is :"+joke);
        if(mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this,this,this);
        asyncTask.execute(joke);
        //Intent intent = new Intent(this, JokeTellingActivityLib.class);
        //intent.putExtra(JOKE_KEY,joke);
        //startActivity(intent);
    }

    /**
     *
     * @param jokeString String Received from Google Cloud EndPoints
     * 1 - This Method receives data and sends it to Android Library
     * 2 - Stoping ProgressBar to complete process
     */
    @Override
    public void getString(String jokeString) {
        //Toast.makeText(this, jokeString, Toast.LENGTH_LONG).show();
        progress.setVisibility(View.GONE);
        Log.d(TAG, "getString: Stopping ProgressBar");
        Intent intent = new Intent(this, JokeTellingActivityLib.class);
        intent.putExtra(JOKE_KEY,jokeString);
        startActivity(intent);
    }

    private void showAds(){
        AdView mAdView = findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    /**
     *
     * @param isStarted The Process of Fetching is Started or not
     *                  If started than showing ProgressBar
     */
    @Override
    public void startedProgress(boolean isStarted) {
        Log.d(TAG, "tellJoke: Starting ProgressBar");
        progress.setVisibility(View.VISIBLE);
    }

    private void initlizeInterAdd(){
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.intertial_ad_unit_id));
        mInterstitialAd.loadAd(
                new AdRequest.Builder()
                        .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).
                        build()
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_about_us:
                Intent intent = new Intent(MainActivityNew.this,AboutUsActivity.class);
                startActivity(intent);
                break;

            case R.id.action_settings:
                Intent settingsIntent = new Intent(MainActivityNew.this,SettingsActivity.class);
                startActivity(settingsIntent);
                break;
        }
        return true;
    }
}
