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
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class FromCharacterQuizActivity extends AppCompatActivity {

    private ArrayList<Character> vowels = new ArrayList<>();
    private ArrayList<Character> consonants = new ArrayList<>();
    private ArrayList<Button> answerButtons = new ArrayList<>();

    // These refer to positions in the original arrays.
    private ArrayList<Integer> activeVowels = new ArrayList<>();
    private ArrayList<Integer> activeConsonants = new ArrayList<>();

    // Pool of transcriptions to choose from.
    private ArrayList<String> transcriptions = new ArrayList<>();

    // Currently selected character
    private Character chosen;

    private PopupWindow goBackWindow;
    Random rng = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_from_character_quiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.blue));
        setSupportActionBar(toolbar);

        vowels = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.vowels");
        consonants = (ArrayList<Character>) getIntent().getExtras().get("com.learnhangul.learnhangul.consonants");

        // Add the characters considered for the quiz, since that doesn't change in this Activity.
        int nVowels = vowels.size();
        int nConsonants = consonants.size();

        for(int i = 0 ; i < nVowels ; i++) {

            transcriptions.add(vowels.get(i).getTranscription());

            if (vowels.get(i).isActive())

                activeVowels.add(i);

        }

        for(int i = 0 ; i < nConsonants ; i++) {

            transcriptions.add(consonants.get(i).getTranscription());

            if (consonants.get(i).isActive())

                activeConsonants.add(i);

        }

        answerButtons.add((Button) findViewById(R.id.quiz_first_option));
        answerButtons.add((Button) findViewById(R.id.quiz_second_option));
        answerButtons.add((Button) findViewById(R.id.quiz_third_option));
        answerButtons.add((Button) findViewById(R.id.quiz_fourth_option));
        answerButtons.add((Button) findViewById(R.id.quiz_fifth_option));
        answerButtons.add((Button) findViewById(R.id.quiz_sixth_option));

        for(Button b: answerButtons)

            setAnswerButton(b);

        setNextQuestion();

    }

    @Override
    public void onBackPressed(){

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.popup_quiz,(ViewGroup) findViewById(R.id.layout_popup_quiz));
        goBackWindow = new PopupWindow(popupView, Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT,true);
        goBackWindow.showAtLocation(findViewById(R.id.layout_quiz_from_character), Gravity.CENTER,0,0);

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
        Intent backToReview = new Intent();
        backToReview.putExtra("com.learnhangul.learnhangul.vowels",vowels);
        backToReview.putExtra("com.learnhangul.learnhangul.consonants",consonants);
        setResult(RESULT_OK,backToReview);
        finish();

    }

    public void setAnswerButton(Button b){

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button button = (Button) view;
                String transcription = button.getText().toString();
                RelativeLayout result = (RelativeLayout) findViewById(R.id.layout_quiz_result);
                TextView result_text = (TextView) findViewById(R.id.quiz_result_text);

                // Reward for correct answer (limits specified in Character class)
                if(transcription.equals(chosen.getTranscription())) {

                    chosen.incrementLearnRating();
                    result.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.selected_green_learned));
                    result_text.setText(getResources().getText(R.string.answer_correct_pre)
                                        + chosen.getCharacter()
                                        + getResources().getText(R.string.answer_mid)
                                        + chosen.getTranscription() + ".");

                }

                else { // Penalization for wrong answer

                    chosen.decrementLearnRating();
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

        int [] chosen = new int[2];

        // If we can take one character from each set.
        if(!activeVowels.isEmpty() && !activeConsonants.isEmpty()) {

            chosen[0] = rng.nextInt(activeVowels.size()); // We choose a candidate vowel
            chosen[1] = rng.nextInt(activeConsonants.size());

            // We return the character that needs more practice out of the two
            if(vowels.get(activeVowels.get(chosen[0])).getLearnRating() >
                    consonants.get(activeConsonants.get(chosen[1])).getLearnRating())

                return consonants.get(activeConsonants.get(chosen[1]));

            else // This actually creates a bias for returning vowels

                return vowels.get(activeVowels.get(chosen[0]));

        }

        // If we can choose from 2 or more only from the vowels set.
        else if(activeVowels.size() > 1){

            chosen[0] = rng.nextInt(activeVowels.size());
            chosen[1] = rng.nextInt(activeVowels.size());

            if(vowels.get(activeVowels.get(chosen[0])).getLearnRating() >
                    vowels.get(activeVowels.get(chosen[1])).getLearnRating())

                return vowels.get(activeVowels.get(chosen[1]));

            else

                return vowels.get(activeVowels.get(chosen[0]));
        }

        // If we can choose from 2 or more only from the vowels set.
        else if(activeConsonants.size() > 1){

            chosen[0] = rng.nextInt(activeConsonants.size());
            chosen[1] = rng.nextInt(activeConsonants.size());

            if(consonants.get(activeConsonants.get(chosen[0])).getLearnRating() >
                    consonants.get(activeConsonants.get(chosen[1])).getLearnRating())

                return consonants.get(activeConsonants.get(chosen[1]));

            else

                return consonants.get(activeConsonants.get(chosen[0]));

        }

        // If we only have one vowel (return it)
        else if(!activeVowels.isEmpty())

            return vowels.get(activeVowels.get(0));

        else // If we only have one consonant (return it)

            return consonants.get(activeConsonants.get(0));

    }

    // Mix the correct answer with another 5 random ones and put them in buttons
    public void setNextQuestion(){

        chosen = chooseNext();
        HashSet<String> possibleTranscriptions = new HashSet<>();
        TextView characterText = (TextView) findViewById(R.id.quiz_character);
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
