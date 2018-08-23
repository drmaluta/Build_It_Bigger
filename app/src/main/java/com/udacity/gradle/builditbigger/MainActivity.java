package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.maluta.jokeactivity.JokeActivity;
import com.maluta.jokeslib.Jokes;


public class MainActivity extends AppCompatActivity {
    public static final String JOKE_KEY_EXTRA = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        JokeAsyncTask.OnJokeLoaded callback = new JokeAsyncTask.OnJokeLoaded() {
            @Override
            public void success(String result) {
                Intent myIntent = new Intent(MainActivity.this, JokeActivity.class);
                myIntent.putExtra(JOKE_KEY_EXTRA, result);
                Toast.makeText(MainActivity.this, result, Toast.LENGTH_SHORT).show();
                startActivity(myIntent);
            }
        };
        //Jokes myJokes = new Jokes();
        //String joke = myJokes.getJoke();
        //Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        new JokeAsyncTask(callback).execute();


    }


}
