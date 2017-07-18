package com.learnhangul.learnhangul;

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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class FromSCharacterQuizActivity extends AppCompatActivity {

    private ArrayList<Character> syllables;
    private ArrayList<Button> answerButtons = new ArrayList<>();

    // Pool of transcriptions to choose from.
    private ArrayList<String> transcriptions = new ArrayList<>();

    // Currently selected character
    private Character chosen;

    private PopupWindow goBackWindow;
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_syllable_character_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setTitleMarginStart(90);
        toolbar.setTitle(getString(R.string.toolbar_title_from_syllable_character));
        setSupportActionBar(toolbar);

        syllables = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.syllables");

        // Fill the pool of transcriptions to choose from as possible answers to a question.
        for(Character c: syllables)

            transcriptions.add(c.getTranscription());

        answerButtons.add((Button) findViewById(R.id.quiz3_first_option));
        answerButtons.add((Button) findViewById(R.id.quiz3_second_option));
        answerButtons.add((Button) findViewById(R.id.quiz3_third_option));
        answerButtons.add((Button) findViewById(R.id.quiz3_fourth_option));
        answerButtons.add((Button) findViewById(R.id.quiz3_fifth_option));
        answerButtons.add((Button) findViewById(R.id.quiz3_sixth_option));

        for(Button b: answerButtons)

            setAnswerButton(b);

        setNextQuestion();

    }

    @Override
    public void onBackPressed(){

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_quiz,(ViewGroup) findViewById(R.id.layout_popup_quiz));
        goBackWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT,true);
        goBackWindow.showAtLocation(findViewById(R.id.layout_quiz_from_syllable_character), Gravity.CENTER,0,0);

        final Button cancel = (Button) popupView.findViewById(R.id.popup_quiz_no);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                goBackWindow.dismiss();

            }
        });

        final Button accept = (Button) popupView.findViewById(R.id.popup_quiz_yes);
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
        finish();

    }

    public void setAnswerButton(Button b){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button button = (Button) view;
                String transcription = button.getText().toString();
                RelativeLayout result = (RelativeLayout) findViewById(R.id.layout_quiz3_result);
                TextView result_text = (TextView) findViewById(R.id.quiz3_result_text);

                // Notification for correct answer. When using syllables, there are no progress changes.
                if(transcription.equals(chosen.getTranscription())) {

                    result.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.selected_green_learned));
                    result_text.setText(getResources().getText(R.string.answer_correct_pre)
                            + chosen.getCharacter()
                            + getResources().getText(R.string.answer_mid)
                            + chosen.getTranscription() + ".");

                }

                else { // Notification for wrong answer.

                    result.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.selected_red_difficult));
                    result_text.setText(getResources().getText(R.string.answer_incorrect_pre)
                            + chosen.getCharacter()
                            + getResources().getText(R.string.answer_mid)
                            + chosen.getTranscription() + ".");

                }

                setNextQuestion();

            }
        });
    }

    // Choose a character for the next question favoring those with lower learn ratings
    public Character chooseNext(){

        return syllables.get(rng.nextInt(syllables.size()));

    }

    // Mix the correct answer with another 5 random ones and put them in buttons
    public void setNextQuestion(){

        chosen = chooseNext();
        HashSet<String> possibleTranscriptions = new HashSet<>();
        TextView characterText = (TextView) findViewById(R.id.quiz3_syllable_character);
        characterText.setText(chosen.getCharacter());
        possibleTranscriptions.add(chosen.getTranscription());

        while(possibleTranscriptions.size() < 6) // A set makes avoiding repetitions easier

            possibleTranscriptions.add(transcriptions.get(rng.nextInt(transcriptions.size())));

        int buttonIndex = 0;

        for(String s: possibleTranscriptions) {

            answerButtons.get(buttonIndex).setText(s);
            buttonIndex += 1;

        }
    }
}
