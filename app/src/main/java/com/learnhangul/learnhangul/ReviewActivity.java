package com.learnhangul.learnhangul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ReviewActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();
    //private ArrayList<Character> syllables = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.red));
        setSupportActionBar(toolbar);

        vowels = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.vowels");
        consonants = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.consonants");
        //syllables = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.syllables");

        setFromCharacterButton();
        setFromTranscriptionButton();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        super.onActivityResult(requestCode,resultCode,data);

        if(requestCode == 1){

            if(resultCode == RESULT_OK){

                vowels = (ArrayList<Character>) data.getExtras().get("com.learnhangul.learnhangul.vowels");
                consonants = (ArrayList<Character>) data.getExtras().get("com.learnhangul.learnhangul.consonants");

            }
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

    private void setFromCharacterButton(){

        final Button study = (Button) findViewById(R.id.button_review_from_character);
        study.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent goCharacterQuiz = new Intent(ReviewActivity.this, FromCharacterQuizActivity.class);
                goCharacterQuiz.putExtra("com.learnhangul.learnhangul.vowels",vowels);
                goCharacterQuiz.putExtra("com.learnhangul.learnhangul.consonants",consonants);
                //goCharacterQuiz.putExtra("com.learnhangul.learnhangul.syllables",syllables);
                startActivityForResult(goCharacterQuiz,1);

            }
        });
    }

    private void setFromTranscriptionButton(){

        final Button study = (Button) findViewById(R.id.button_review_from_transcription);
        study.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent goTranscriptionQuiz = new Intent(ReviewActivity.this, FromTranscriptionQuizActivity.class);
                goTranscriptionQuiz.putExtra("com.learnhangul.learnhangul.vowels",vowels);
                goTranscriptionQuiz.putExtra("com.learnhangul.learnhangul.consonants",consonants);
                //goTranscriptionQuiz.putExtra("com.learnhangul.learnhangul.syllables",syllables);
                startActivityForResult(goTranscriptionQuiz,1);

            }
        });
    }
}
