package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;


/**
 * Created by admin on 8/23/2018.
 */

@RunWith(AndroidJUnit4.class)
public class JokeAsyncTaskTest {
    private String mJoke;
    private final String TAG = JokeAsyncTaskTest.class.getSimpleName();

    @Test
    public void jokeNotNullTest() {
        try {
            final CountDownLatch signal = new CountDownLatch(1);
            JokeAsyncTask task = new JokeAsyncTask();
            task.setListener(new JokeAsyncTask.OnJokeLoaded() {
                @Override
                public void success(String result) {
                    mJoke = result;
                    signal.countDown();
                }
            }).execute();
            signal.await();
            assertFalse("The retrived joke is not an error text", mJoke.equalsIgnoreCase(JokeAsyncTask.AsyncError));
            assertTrue("The retrieved joke must not be empty", mJoke != null
                    && !mJoke.isEmpty() && mJoke.length() > 0);

        } catch (InterruptedException e) {
            Log.d(TAG, e.getMessage());
            Assert.fail("Test Failed due to exceptions ");
        }
    }
}
