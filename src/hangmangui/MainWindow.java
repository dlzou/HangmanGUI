package hangmangui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.text.*;

public class MainWindow extends JFrame {
    
    private JPanel outputPanel;
    private JLabel graphic;
    private JLabel currentWord;
    private JLabel usedLetters;
    private JLabel messageLabel;
    
    private JPanel inputPanel;
    private JLabel promptLabel;
    private JTextField inputField;
    private JButton inputButton;
    
    public MainWindow() {
        initComponents();
    } //MainWindow
    
    public void initComponents() {
        outputPanel = new JPanel();
        graphic = new JLabel(" ");
        currentWord = new JLabel(" ");
        usedLetters = new JLabel(" ");
        messageLabel = new JLabel(" ");
        
        inputPanel = new JPanel();
        promptLabel = new JLabel("Guess a letter:");
        inputField = new JTextField(5);
        inputButton = new JButton("Enter");
        
        HangmanModel model = new HangmanModel();
        Controller controller = new Controller(model, this);
        inputButton.addActionListener(controller);
        
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setTitle("Hangman");
        
        //outputPanel
        outputPanel.setLayout(new BoxLayout(outputPanel, BoxLayout.Y_AXIS));
        outputPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        graphic.setAlignmentX(Component.CENTER_ALIGNMENT);
        outputPanel.add(graphic);
        
        currentWord.setAlignmentX(Component.CENTER_ALIGNMENT);
        currentWord.setFont(new Font("SansSerif", Font.PLAIN, 36));
        outputPanel.add(currentWord);
        
        usedLetters.setAlignmentX(Component.CENTER_ALIGNMENT);
        usedLetters.setFont(new Font("SansSerif", Font.PLAIN, 18));
        outputPanel.add(usedLetters);
        
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        outputPanel.add(messageLabel);
        
        //inputPanel
        inputPanel.setLayout(new FlowLayout());
        inputPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        
        promptLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
        inputPanel.add(promptLabel);
        
        inputField.setDocument(new JTextFieldLimit(1));
        inputField.setHorizontalAlignment(JTextField.CENTER);
        inputField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        inputPanel.add(inputField);
        
        inputPanel.add(inputButton);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(10, 10, 5, 10);
        c.weighty = 1.0;
        add(outputPanel, c);
        
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5, 10, 10, 10);
        c.weighty = 0.0;
        add(inputPanel, c);
        
        pack();
        setMinimumSize(getSize());
    } //initComponent
    
    public JLabel getGraphic() {
        return graphic;
    } //getGraphic
    
    public JLabel getCurrentWord() {
        return currentWord;
    } //getCurrentWord
    
    public JLabel getUsedLetters() {
        return usedLetters;
    } //getUsedLetters
    
    public JLabel getMessageLabel() {
        return messageLabel;
    } //getMessageLabel
            
    public JButton getInputButton() {
        return inputButton;
    } //getInputButton
    
    public JTextField getInputField() {
        return inputField;
    } //getInputField
    
} //MainWindow



class JTextFieldLimit extends PlainDocument {
    private int limit;

    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    } //JTextFieldLimit
    
    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null)
            return;

        if ((getLength() + str.length() <= limit) && str.chars().allMatch(Character::isLetter)) {
            super.insertString(offset, str, attr);
        } //if
    } //insertString
} //JTextFieldLimit
