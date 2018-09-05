package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import static com.vyas.pranav.androidjokelib.JokeTellingActivityLib.ErrorDefaultString;

public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private static MyApi myApiService = null;
    private final Context context;
    private final AsyncCallback mCallback;
    private final AsyncCallbackBegin mBeginCallback;
    private String name;

    public EndpointsAsyncTask(Context context,AsyncCallback mCallback,AsyncCallbackBegin mBeginCallback) {
        this.context = context;
        this.mCallback = mCallback;
        this.mBeginCallback = mBeginCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mBeginCallback.startedProgress(true);
    }

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://"+context.getString(R.string.IP_ADDRESS)+":8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }
        name = params[0];
        try {
            String str = myApiService.sayHi(name).execute().getData().substring(4);
            return str;
        } catch (IOException e) {
            return ErrorDefaultString+e.getMessage();
        }

    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.getString(result);
    }

    public interface AsyncCallback{
        void getString(String jokeString);
    }

    public interface AsyncCallbackBegin{
        void startedProgress(boolean isStarted);
    }
}