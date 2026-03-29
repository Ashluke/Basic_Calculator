package src;
import javax.swing.*;
import java.awt.*;

public class Main{
    // Instance variables
    static String input = "";
    static JLabel display;

    public static void main(String[] args){
        // Frame
        JFrame frame = new JFrame("GridSizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        // Display output panel
        JPanel box = new JPanel(new GridLayout(0, 1, 5,5));
        box.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        box.setPreferredSize(new Dimension(600, 100));
        display = new JLabel("", SwingConstants.RIGHT);
        display.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        box.add(display);
        container.add(box);

        // Button panel
        JPanel panel = new JPanel(new GridLayout(0, 4, 5, 5));
        panel.setPreferredSize(new Dimension(600, 500));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        ButtonCreator.createButtons(panel);
        container.add(panel);

        frame.pack();
        frame.setVisible(true);
    }

    // Action listeners via switch
    public static void handleInput(String text){
        switch(text){

            // Clear button
            case "CLS":
                input = "";
                display.setText(input);
                break;
            
            // Back or Delete button
            case "DEL":
                if(!input.isEmpty()){
                    input = input.substring(0, input.length() - 1);
                }
                display.setText(input);
                break;
            
            // Exit app button
            case "CLOSE":
                System.exit(0);
            
            // Define. Calls out parser class function.
            case "=":
                if(!input.isEmpty()){
                    // Runs code
                    try{
                        double result = Parser.eval(input);

                        if(result == (int)result){
                            display.setText("" + (int)result);
                            input = String.valueOf((int)result);
                        }
                        else{
                            display.setText("" + result);
                            input = String.valueOf(result);
                        }
                    }

                    // Handles error
                    catch (Exception e){
                        display.setText("ERROR");
                        input = "";
                    }
                }
                break;
            
            default:
                input += text;
                display.setText(input);
        }
    }
}
