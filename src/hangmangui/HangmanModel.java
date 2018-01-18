package hangmangui;

import java.util.ArrayList;

public class HangmanModel {
    
    private String word;
    private int guessesLeft;
    private ArrayList<String> currentWord;
    private ArrayList<String> usedLetters;
    
    public HangmanModel() {
        Dictionary.readFile();
        init();
    } //HangmanModel
    
    public void init() {
        word = Dictionary.getHard();
        guessesLeft = 6;
        
        currentWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++)
            currentWord.add("_");
        
        usedLetters = new ArrayList<>();
    } //reset
    
    public String getWord() {
        return word;
    } //getWord
    
    public int getGuessesLeft() {
        return guessesLeft;
    } //getGuessesLeft
    
    public ArrayList<String> getCurrentWord() {
        return currentWord;
    } //getCurrentWord
    
    public ArrayList<String> getUsedLetters() {
        return usedLetters;
    } //getWord
    
    public void loseGuess() {
        guessesLeft--;
    } //loseGuess
    
} //HangmanModel
