package com.learnhangul.learnhangul;

import java.io.Serializable;

class Character implements Serializable{

    private static final int MAX_LEARN_RATING = 7;
    private static final int MIN_LEARN_RATING = -1;

    private String character = new String(); // The character itself.
    private String pronunciation = new String(); // The pronunciation in Latin alphabet.
    private int learnRating = 0; // The progress in learning this particular character.

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private boolean active = false;

    public int getLearnRating() {
        return learnRating;
    }

    public void setLearnRating(int learnRating) {
        if(learnRating >= MAX_LEARN_RATING)
            this.learnRating = MAX_LEARN_RATING;
        else if(learnRating <= MIN_LEARN_RATING)
            this.learnRating = MIN_LEARN_RATING;
        else
            this.learnRating = learnRating;
    }

    public void incrementLearnRating(){
        if(this.learnRating < MAX_LEARN_RATING)
            this.learnRating += 1;
    }

    public void decrementLearnRating(){
        if(this.learnRating > MIN_LEARN_RATING)
            this.learnRating -= 1;
    }

    public String getPronunciation() {
        return pronunciation;
    }

    public void setPronunciation(String pronunciation) {
        this.pronunciation = pronunciation;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString(){

        return character + " " + pronunciation + " " + learnRating + " " + Boolean.toString(active);

    }

}
