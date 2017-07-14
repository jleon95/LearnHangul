package com.learnhangul.learnhangul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class StudyActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
        setSupportActionBar(toolbar);

        vowels = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.vowels");
        consonants = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.consonants");

        setVowelsButton();
        setConsonantsButton();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 1){

            if(resultCode == RESULT_OK)

                vowels = (ArrayList<Character>) data.getExtras().get("com.learnhangul.learnhangul.characters");

        }

        if(requestCode == 2){

            if(resultCode == RESULT_OK)

                consonants = (ArrayList<Character>) data.getExtras().get("com.learnhangul.learnhangul.characters");

        }
    }

    @Override
    public void onBackPressed(){

        Intent backToMain = new Intent();
        backToMain.putExtra("com.learnhangul.learnhangul.vowels",vowels);
        backToMain.putExtra("com.learnhangul.learnhangul.consonants",consonants);
        setResult(RESULT_OK,backToMain);
        super.onBackPressed();

    }

    private void setVowelsButton(){

        final Button study = (Button) findViewById(R.id.button_study_vowels);
        study.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent goVowels = new Intent(StudyActivity.this, CharactersActivity.class);
                goVowels.putExtra("com.learnhangul.learnhangul.characters",vowels);
                startActivityForResult(goVowels,1);

            }
        });
    }

    private void setConsonantsButton(){

        final Button study = (Button) findViewById(R.id.button_study_consonants);
        study.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent goConsonants = new Intent(StudyActivity.this, CharactersActivity.class);
                goConsonants.putExtra("com.learnhangul.learnhangul.characters",consonants);
                startActivityForResult(goConsonants,2);

            }
        });
    }
}
