package hangmangui;

/**
 * Daniel Zou
 * @author lenovo
 */

import java.awt.*;

public class HangmanGUI {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    } //main
    
} //HangmanGUI
