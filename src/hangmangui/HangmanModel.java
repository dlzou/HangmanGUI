package hangmangui;

import java.io.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class HangmanModel {
    
    private String word;
    private int guessesLeft;
    private ArrayList<String> currentWord;
    private ArrayList<String> usedLetters;
    
    private ArrayList<String> dictionary;
    private HashMap<Integer, ImageIcon> images;
    
    public HangmanModel() {
        loadDictionary();
        loadImages();
    } //HangmanModel
    
    public void init() {
        word = newWord();
        guessesLeft = 6;
        
        currentWord = new ArrayList<>();
        for (int i = 0; i < word.length(); i++)
            currentWord.add("_");
        
        usedLetters = new ArrayList<>();
    } //reset
    
    public void loadDictionary() {
        dictionary = new ArrayList<>();
        
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader("dictionary.txt"));
            String buffer = null;
            while ((buffer = inputFile.readLine()) != null) {
                buffer = buffer.toLowerCase();
                dictionary.add(buffer);
            } //while
            inputFile.close();
        } //try
        catch (IOException e) {
            e.printStackTrace();
        } //catch
    } //loadDictionary
    
    public String newWord() {
        int index = (int)(Math.random() * dictionary.size());
        return dictionary.get(index);
    } //newWord
    
    public void loadImages() {
        images = new HashMap<>();
        File folder = new File("images/");
        File[] rawImages = folder.listFiles();
        Arrays.sort(rawImages);
        
        int index = 0;
        for (File image : rawImages) {
            if (image.getPath().endsWith(".png")) {
                images.put(index, new ImageIcon(image.getPath()));
                index++;
            } //if
        } //for
    } //loadImage
    
     public ImageIcon getCurrentImage() {
         return images.get(guessesLeft);
     } //getCurrentImage
    
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
