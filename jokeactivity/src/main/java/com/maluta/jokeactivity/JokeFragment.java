package com.maluta.jokeactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by admin on 8/24/2018.
 */

public class JokeFragment extends Fragment {
    public static final String JOKE_KEY_EXTRA = "joke";

    //empty constructor
    public JokeFragment () {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_joke, container, false);
        setJokeTV(view);

        return view;
    }

    private void setJokeTV(View view) {
        Intent intent = getActivity().getIntent();
        String joke = intent.getStringExtra(JOKE_KEY_EXTRA);
        if (joke != null) {
            TextView textView = view.findViewById(R.id.tv_joke);
            textView.setText(joke);
        }
    }
}
