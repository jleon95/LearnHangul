package com.learnhangul.learnhangul;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
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
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class FromCharacterQuizActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();

    private PopupWindow goBackWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_character_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        setSupportActionBar(toolbar);

        vowels = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.vowels");
        consonants = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.consonants");

    }

    @Override
    public void onBackPressed(){

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_quiz,(ViewGroup) findViewById(R.id.layout_popup_quiz_from_character));
        goBackWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT,true);
        goBackWindow.showAtLocation(findViewById(R.id.layout_quiz_from_character), Gravity.CENTER,0,0);

        final Button cancel = (Button) popupView.findViewById(R.id.popup_quiz_from_character_no);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                goBackWindow.dismiss();

            }
        });

        final Button accept = (Button) popupView.findViewById(R.id.popup_quiz_from_character_yes);
        accept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                goBackWindow.dismiss();
                onStop();

            }
        });
    }

    @Override
    protected void onStop(){

        super.onStop();
        Intent backToReview = new Intent();
        backToReview.putExtra("com.learnhangul.learnhangul.vowels",vowels);
        backToReview.putExtra("com.learnhangul.learnhangul.consonants",consonants);
        setResult(RESULT_OK,backToReview);
        finish();

    }
}
