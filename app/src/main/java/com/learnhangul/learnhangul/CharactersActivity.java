package com.learnhangul.learnhangul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
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
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleMarginStart(90);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        characters = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.characters");

        if(characters.get(0).getCharacter().equals("ㅏ"))

            toolbar.setTitle(getString(R.string.toolbar_title_vowels));

        else

            toolbar.setTitle(getString(R.string.toolbar_title_consonants));

        setCharacterList();

    }

    @Override
    public void onBackPressed(){

        Intent backToStudy = new Intent();
        backToStudy.putExtra("com.learnhangul.learnhangul.characters",characters);
        setResult(RESULT_OK,backToStudy);
        super.onBackPressed();

    }

    private void setCharacterList(){

        ListView character_list = (ListView) findViewById(R.id.character_list);
        final ArrayAdapter<Character> adapter = new RowAdapter(this,characters);
        character_list.setAdapter(adapter);

        character_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                boolean isSelected = characters.get(i).isActive() ? false : true;
                characters.get(i).setActive(isSelected);
                adapter.notifyDataSetChanged();

            }
        });

    }
}
