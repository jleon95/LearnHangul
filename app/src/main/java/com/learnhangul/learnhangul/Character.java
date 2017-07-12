package com.learnhangul.learnhangul;

/**
 * Created by javi on 12/07/17.
 */

class Character {

    private String character = new String(); // The character itself.
    private String pronunciation = new String(); // The pronunciation in Latin alphabet.
    private int learnRating = 0; // The progress in learning this particular character.

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

}
