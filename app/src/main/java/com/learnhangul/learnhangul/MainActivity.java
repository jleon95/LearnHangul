package com.learnhangul.learnhangul;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();
    //private ArrayList<Character> syllables = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try{

            ReadCharactersFromFile(String.valueOf(R.raw.vowels),false);

        } catch (Exception e) { // If there isn't a file, create a new one with no progress.

            System.err.println(getResources().getString(R.string.error_load_vowels_progress));

            try {

                ReadCharactersFromFile(String.valueOf(R.raw.vowels), true);

            } catch (IOException ioe) {

                System.err.println(getResources().getString(R.string.error_load_vowels_default));

            }
        }
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

    private void ReadCharactersFromFile(String fileName, boolean isDefault) throws IOException{

        String line;
        Character c = new Character();

        if(isDefault){

            InputStream isv = getApplicationContext().getResources().openRawResource(Integer.parseInt(fileName));
            InputStreamReader isrv = new InputStreamReader(isv);
            BufferedReader bufferedReader = new BufferedReader(isrv);
            while((line = bufferedReader.readLine()) != null){

                String [] contents = line.split(" ");
                c.setCharacter(contents[0]);
                c.setPronunciation(contents[1]);
                c.setLearnRating(0);
                vowels.add(c);

            }
        }

        else{

            FileInputStream fisv = getApplicationContext().openFileInput(fileName);
            InputStreamReader isrv = new InputStreamReader(fisv);
            BufferedReader bufferedReader = new BufferedReader(isrv);
            while((line = bufferedReader.readLine()) != null){

                String [] contents = line.split(" ");
                c.setCharacter(contents[0]);
                c.setPronunciation(contents[1]);
                c.setLearnRating(Integer.parseInt(contents[2]));
                vowels.add(c);

            }
        }
    }
}
