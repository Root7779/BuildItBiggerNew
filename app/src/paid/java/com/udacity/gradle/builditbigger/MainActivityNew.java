package com.udacity.gradle.builditbigger;

/*
  This is A paid Version Source code for button to send data to Android Library
 */

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vyas.pranav.androidjokelib.JokeTellingActivityLib;
import com.vyas.pranav.javajokelibrary.Joker;

import static com.vyas.pranav.androidjokelib.JokeTellingActivityLib.JOKE_KEY;

public class MainActivityNew extends AppCompatActivity implements EndpointsAsyncTask.AsyncCallback,EndpointsAsyncTask.AsyncCallbackBegin{
    private static final String TAG = "MainActivityNewPaid";
    private ProgressBar progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = findViewById(R.id.progressBarMain);
        showIntro();
    }

    //Showing Intro for First Time
    private void showIntro(){
        if(SharedPrefsUtils.getFirstTimeForFile(this,"FIRST_TIME")){
            new AlertDialog.Builder(this)
                    .setMessage("Thank you for Installing Paid App\nYou just gave me money for eating today's dinner ( in 5 Star Hotel;) ) !!!\nKeep this good work Up")
                    .setTitle("Paid Version")
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
        //Toast.makeText(this, "Library Returned : "+joke, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "tellJoke: Joke is :"+joke);
        //TODO Remove 2 Comment while completeed Testing
        //EndpointsAsyncTask asyncTask = new EndpointsAsyncTask(this,this,this);
        //asyncTask.execute(joke);
        Intent intent = new Intent(this, JokeTellingActivityLib.class);
        intent.putExtra(JOKE_KEY,joke);
        startActivity(intent);
    }

    /**
     *
     * @param jokeString String Received from Google Cloud EndPoints
     * 1 - This Method receives data and sends it to Android Library
     * 2 - Stoping ProgressBar to complete process
     */
    @Override
    public void getString(String jokeString) {
        Toast.makeText(this, jokeString, Toast.LENGTH_LONG).show();
        progress.setVisibility(View.GONE);
        Log.d(TAG, "getString: Stopping ProgressBar");
        Intent intent = new Intent(this, JokeTellingActivityLib.class);
        intent.putExtra(JOKE_KEY,jokeString);
        startActivity(intent);
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
        }
        return true;
    }
}
