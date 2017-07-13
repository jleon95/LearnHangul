package com.learnhangul.learnhangul;

import java.io.Serializable;

/**
 * Created by javi on 12/07/17.
 */

class Character implements Serializable{

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
        this.learnRating = learnRating;
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
