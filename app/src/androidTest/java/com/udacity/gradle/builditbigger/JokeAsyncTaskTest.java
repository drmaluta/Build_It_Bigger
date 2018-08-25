package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by admin on 8/23/2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {

    @Test
    public void jokeNotNullTest() {

        JokeAsyncTask.OnJokeLoaded callback = new JokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                Log.d("testing", "Retrieved a non-empty string successfully: " + result);
                assertNotNull(result);
            }
        };
        new JokeAsyncTask(callback).execute();

    }
}
