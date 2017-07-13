package com.learnhangul.learnhangul;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

            vowels = ReadCharactersFromFile(String.valueOf(R.raw.vowels),false);

        } catch (Exception e) { // If there isn't a file, create a new one with no progress.

            System.err.println(getResources().getString(R.string.error_load_vowels_progress));

            try {

                vowels = ReadCharactersFromFile(String.valueOf(R.raw.vowels), true);

            } catch (IOException ioe) {

                System.err.println(getResources().getString(R.string.error_load_vowels_default));

            }
        }

        try{

            consonants = ReadCharactersFromFile(String.valueOf(R.raw.consonants),false);

        } catch (Exception e) { // If there isn't a file, create a new one with no progress.

            System.err.println(getResources().getString(R.string.error_load_consonants_progress));

            try {

                consonants = ReadCharactersFromFile(String.valueOf(R.raw.consonants), true);

            } catch (IOException ioe) {

                System.err.println(getResources().getString(R.string.error_load_consonants_default));

            }
        }

        //ListView listaview = (ListView) findViewById(R.id.test_list);
        //ArrayAdapter<Character> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,android.R.id.text1,vowels);
        //listaview.setAdapter(adapter);
    }

    @Override
    protected void onDestroy(){

        try {

            SaveCharactersToFile("vowels.txt", vowels);

        } catch (IOException ioe){

            System.err.println(getResources().getString(R.string.error_save_vowels_progress));

        }

        try {

            SaveCharactersToFile("consonants.txt",consonants);

        } catch (IOException ioe){

            System.err.println(getResources().getString(R.string.error_save_consonants_progress));

        }

        super.onDestroy();

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

    private ArrayList<Character> ReadCharactersFromFile(String fileName, boolean isDefault) throws IOException{

        String line;
        ArrayList<Character> fileCharacters = new ArrayList<>();

        if(isDefault){

            InputStream is = getApplicationContext().getResources().openRawResource(Integer.parseInt(fileName));
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            while((line = bufferedReader.readLine()) != null){

                String [] contents = line.split(" ");
                Character c = new Character();
                c.setCharacter(contents[0]);
                c.setPronunciation(contents[1]);
                c.setLearnRating(0);
                c.setActive(false);
                fileCharacters.add(c);

            }
        }

        else{

            FileInputStream fis = getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            while((line = bufferedReader.readLine()) != null){

                String [] contents = line.split(" ");
                Character c = new Character();
                c.setCharacter(contents[0]);
                c.setPronunciation(contents[1]);
                c.setLearnRating(Integer.parseInt(contents[2]));
                c.setActive(Boolean.parseBoolean(contents[3]));
                fileCharacters.add(c);

            }
        }

        return fileCharacters;
    }

    private void SaveCharactersToFile(String fileName, ArrayList<Character> characters) throws IOException{

        FileOutputStream outputFStream = openFileOutput(fileName, Context.MODE_PRIVATE);
        for(Character c: characters)

            outputFStream.write((c.toString()+"\n").getBytes());

        outputFStream.close();
    }
}
