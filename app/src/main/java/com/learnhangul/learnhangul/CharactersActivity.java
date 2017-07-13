package com.learnhangul.learnhangul;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CharactersActivity extends AppCompatActivity {

    private ArrayList<Character> characters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_characters);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        characters = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.characters");

        ListView character_list = (ListView) findViewById(R.id.character_list);
        ArrayAdapter<Character> adapter = new RowAdapter(this,characters);
        character_list.setAdapter(adapter);

    }
}
