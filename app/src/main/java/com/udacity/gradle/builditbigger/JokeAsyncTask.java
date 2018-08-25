package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by admin on 8/22/2018.
 */

public class JokeAsyncTask extends AsyncTask<Void, Void, String> {

    private static MyApi myApi = null;
    private OnJokeLoaded mCallback;
    public static String AsyncError = "Error Getting Joke";
    private static final String LOCALHOST_IP_ADDRESS = "http://10.0.2.2:8080/_ah/api/";

    public interface OnJokeLoaded {
        void success(String result);
    }

    JokeAsyncTask() {

    }

    public JokeAsyncTask setListener (OnJokeLoaded callback){
        mCallback = callback;
        return this;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApi == null) {
            myApi = initializeApi();
        }

        try {
            return myApi.loadJoke().execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return AsyncError;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mCallback.success(result);
    }

    private MyApi initializeApi() {
        MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                new AndroidJsonFactory(), null)
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                .setRootUrl(LOCALHOST_IP_ADDRESS)
                .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                    @Override
                    public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                        abstractGoogleClientRequest.setDisableGZipContent(true);
                    }
                });

        return builder.build();
    }
}
