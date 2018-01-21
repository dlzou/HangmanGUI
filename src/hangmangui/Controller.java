package hangmangui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Controller implements ActionListener, WindowListener {
    
    private HangmanModel model;
    private MainWindow window;
    String inputText;
    
    public Controller(HangmanModel model, MainWindow window) {
        this.model = model;
        this.window = window;
        inputText = "";
    } //Controller
    
    public void newGame() {
        int option = JOptionPane.showConfirmDialog(
                window, "Would you like to start a new game?", 
                "New Game", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            model.init(selectLevel());
            updateWindow();
        } //if
        else {
            System.exit(0);
        } //else
    } //initGame
    
    public int selectLevel() {
        Object[] levels = {"Easy", "Medium", "Hard"};
        return JOptionPane.showOptionDialog(window, "Select a level:", 
                "Level", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, levels, levels[0]);
    } //selectDifficulty
    
    public void updateWindow() {
        window.setImage(model.getCurrentImage());
        
        String currentWord = " ";
        for (String letter : model.getCurrentWord())
            currentWord += letter + " ";
        window.getCurrentWord().setText(currentWord);
        
        String usedLetters = " ";
        for (String letter : model.getUsedLetters())
            usedLetters += letter + " ";
        window.getUsedLetters().setText(usedLetters);
        
        window.pack();
        window.setMinimumSize(window.getSize());
    } //updateWindow
    
    public void win() {
        JOptionPane.showMessageDialog(window, "You win! "
                + "The word is \"" + model.getWord() + ".\"");
        newGame();
    } //win
    
    public void lose() {
        JOptionPane.showMessageDialog(window, "You lose. "
                + "The word is \"" + model.getWord() + ".\"");
        newGame();
    } //lose
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(window.getInputButton())) {
            if (window.getInputField().getText().length() > 0) {
                inputText = window.getInputField().getText().toLowerCase();
                window.getInputField().setText("");

                if (model.getUsedLetters().indexOf(inputText) != -1) {
                    window.getMessageLabel().setForeground(Color.RED);
                    window.getMessageLabel().setText("\"" + inputText + "\" already used");
                } //if
                else if (model.getWord().contains(inputText)) {
                    window.getMessageLabel().setForeground(Color.GREEN);
                    window.getMessageLabel().setText("Nice :)");

                    for (int i = 0; i < model.getWord().length(); i++) {
                        if (model.getWord().substring(i, i+1).equals(inputText))
                            model.getCurrentWord().set(i, inputText);
                    } //for
                    model.getUsedLetters().add(inputText);
                } //else if
                else {
                    window.getMessageLabel().setForeground(Color.RED);
                    window.getMessageLabel().setText("Try again :(");
                    model.getUsedLetters().add(inputText);
                    model.loseGuess();
                } //else

                updateWindow();

                if (model.getCurrentWord().indexOf("_") == -1) {
                    window.getMessageLabel().setText(" ");
                    win();
                } //if
                else if (model.getGuessesLeft() == 0) {
                    window.getMessageLabel().setText(" ");
                    lose();
                } //else if
            } //if
            else {
                window.getMessageLabel().setForeground(Color.RED);
                window.getMessageLabel().setText("Please enter a letter");
            } //else
        } //if
    } //actionPerformed
    
    @Override
    public void windowOpened(WindowEvent e) {
        newGame();
        updateWindow();
    } //windowOpened

    @Override
    public void windowClosing(WindowEvent e) {}

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
    
} //Controller
