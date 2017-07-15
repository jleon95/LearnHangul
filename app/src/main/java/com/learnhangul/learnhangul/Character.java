package com.learnhangul.learnhangul;

import java.io.Serializable;

class Character implements Serializable{

    private static final int MAX_LEARN_RATING = 7;
    private static final int MIN_LEARN_RATING = -1;

    private String character = new String(); // The character itself.
    private String transcription = new String(); // The transcription in Latin alphabet.
    private int learnRating = 0; // The progress in learning this particular character.

    boolean isActive() {
        return active;
    }

    void setActive(boolean active) {
        this.active = active;
    }

    private boolean active = false;

    int getLearnRating() {
        return learnRating;
    }

    void setLearnRating(int learnRating) {
        if(learnRating >= MAX_LEARN_RATING)
            this.learnRating = MAX_LEARN_RATING;
        else if(learnRating <= MIN_LEARN_RATING)
            this.learnRating = MIN_LEARN_RATING;
        else
            this.learnRating = learnRating;
    }

    void incrementLearnRating(){
        if(this.learnRating < MAX_LEARN_RATING)
            this.learnRating += 1;
    }

    void decrementLearnRating(){
        if(this.learnRating > MIN_LEARN_RATING)
            this.learnRating -= 1;
    }

    String getTranscription() {
        return transcription;
    }

    void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString(){

        return character + " " + transcription + " " + learnRating + " " + Boolean.toString(active);

    }

}
