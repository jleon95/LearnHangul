package com.learnhangul.learnhangul;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import java.util.ArrayList;

import static android.R.string.cancel;

public class ReviewActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();
    //private ArrayList<Character> syllables = new ArrayList<>();

    private PopupWindow notStartQuizWindow;


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

                int activeCharacters = 0;

                for(Character c: vowels)

                    if(c.isActive())

                        activeCharacters += 1;

                for(Character c: consonants)

                    if(c.isActive())

                        activeCharacters += 1;

                if(activeCharacters > 1) { // We need at least 2 characters to start doing quizzes

                    Intent goCharacterQuiz = new Intent(ReviewActivity.this, FromCharacterQuizActivity.class);
                    goCharacterQuiz.putExtra("com.learnhangul.learnhangul.vowels", vowels);
                    goCharacterQuiz.putExtra("com.learnhangul.learnhangul.consonants", consonants);
                    //goCharacterQuiz.putExtra("com.learnhangul.learnhangul.syllables",syllables);
                    startActivityForResult(goCharacterQuiz, 1);

                }

                else{

                    LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    final View popupView = inflater.inflate(R.layout.popup_not_quiz,(ViewGroup) findViewById(R.id.layout_popup_not_quiz));
                    notStartQuizWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT,true);
                    notStartQuizWindow.showAtLocation(findViewById(R.id.content_review), Gravity.CENTER,0,0);

                    final Button understood = (Button) popupView.findViewById(R.id.popup_not_quiz_ok);
                    understood.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick(View v){

                            notStartQuizWindow.dismiss();

                        }
                    });
                }
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
