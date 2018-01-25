package hangmangui;

/**
 * Daniel Zou
 * @author lenovo
 */

import java.awt.*;
import javax.swing.UIManager;

public class HangmanGUI {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } //try
        catch (Exception e) {
            e.printStackTrace();
        } //catch
        
        EventQueue.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    } //main
    
} //HangmanGUI
