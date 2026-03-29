package src;
import javax.swing.*;
import java.awt.event.*;

public class ButtonCreator{
    public static void createButtons(JPanel panel){

        // Buttons array
        String[] buttons = {
            "CLS", "DEL", "", "CLOSE",
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
        };

        // For every text in buttons array
        for(String text : buttons){
            JComponent btn;

            // Space creator
            if(text == ""){
                btn = new JLabel(text);
            }

            // Button creator
            else{
                JButton button = new JButton(text);
                button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    Main.handleInput(text);
                    }      
                });

                btn = button;
            }
            panel.add(btn);
        }
    }
}