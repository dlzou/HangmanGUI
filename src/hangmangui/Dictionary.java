package hangmangui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {
    private static ArrayList<String> easyList = new ArrayList<>();
    private static ArrayList<String> mediumList = new ArrayList<>();
    private static ArrayList<String> hardList = new ArrayList<>();
    
    public Dictionary() {
        readFile();
    } //Dictionary
    
    public static String getEasy() {
        int index = (int)(Math.random() * easyList.size());
        return easyList.get(index);
    } //getEasy
    
    public static String getMedium() {
        int index = (int)(Math.random() * mediumList.size());
        return mediumList.get(index);
    } //getMedium
    
    public static String getHard() {
        int index = (int)(Math.random() * hardList.size());
        return hardList.get(index);
    } //getHard
    
    public static void readFile() {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader("wordsHangman.txt"));
            String buffer = null;
            while ((buffer = inputFile.readLine()) != null) {
                buffer = buffer.toLowerCase();
                int length = buffer.length();
                int numCommons = 0;
                int numVowels = 0;
                String currentChar = "";
                
                for (int i = 0; i < length; i++) {
                    currentChar = buffer.substring(i, i+1);
                    if (currentChar.equals("r") || currentChar.equals("s") || currentChar.equals("t") || 
                            currentChar.equals("l") || currentChar.equals("n") || currentChar.equals("e"))
                        numCommons++;
                    if (currentChar.equals("a") || currentChar.equals("e") || currentChar.equals("i") || 
                            currentChar.equals("o") || currentChar.equals("u"))
                        numVowels++;
                } //for
                
                if (length <= 4 && (numCommons >= 1 || numVowels >= 2))
                    easyList.add(buffer);
                else if ((length > 4 && length <= 8) && (numCommons >= 2 || numVowels >= 2))
                    mediumList.add(buffer);
                else
                    hardList.add(buffer);
            } //while
            inputFile.close();
        } catch (IOException e) {
            System.out.println("lol is u dumb");
        } //catch
    }//readFile
} //Dictionary
