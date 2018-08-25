package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.maluta.jokeactivity.JokeActivity;

import java.util.Objects;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ProgressBar progressBar = null;
    private InterstitialAd mInterstitialAd;
    public static final String JOKE_KEY_EXTRA = "joke";
    private final String LOG_TAG = MainActivityFragment.class.getSimpleName();

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        AdView mAdView = (AdView) root.findViewById(R.id.adView);

        mInterstitialAd = new InterstitialAd(Objects.requireNonNull(getContext()));
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                //process the joke Request
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();

                //pre-fetch the next ad
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);

                Log.i(LOG_TAG, "Failing");

                //prefetch the next ad
                mInterstitialAd.loadAd(new AdRequest.Builder().build());

            }

            @Override
            public void onAdLoaded() {
                Log.i(LOG_TAG, "Loading");
                super.onAdLoaded();
            }
        });

        Button button = root.findViewById(R.id.tell_joke_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    progressBar.setVisibility(View.VISIBLE);
                    tellJoke();
                }
            }
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        return root;
    }

    public void tellJoke() {
        JokeAsyncTask.OnJokeLoaded callback = new JokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                Intent myIntent = new Intent(getContext(), JokeActivity.class);
                myIntent.putExtra(JOKE_KEY_EXTRA, result);
                startActivity(myIntent);
                progressBar.setVisibility(View.GONE);
            }
        };
        new JokeAsyncTask(callback).execute();
    }
}
