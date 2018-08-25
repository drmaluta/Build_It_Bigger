package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.maluta.jokeactivity.JokeActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private ProgressBar progressBar = null;
    public static final String JOKE_KEY_EXTRA = "joke";

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = root.findViewById(R.id.tell_joke_button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                tellJoke();
            }
        });

        progressBar = root.findViewById(R.id.joke_progressbar);
        progressBar.setVisibility(View.GONE);

        return root;
    }

    public void tellJoke() {
        JokeAsyncTask task = new JokeAsyncTask();
        task.setListener(new JokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                Intent myIntent = new Intent(getContext(), JokeActivity.class);
                myIntent.putExtra(JOKE_KEY_EXTRA, result);
                progressBar.setVisibility(View.GONE);
                startActivity(myIntent);
            }
        }).execute();

    }
}
