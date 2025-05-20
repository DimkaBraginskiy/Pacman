package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainMenuView extends JPanel {
    private final JButton newGameButton = new JButton("New Game");
    private final JButton highScoresButton = new JButton("High Scores");
    private final JButton exitButton = new JButton("Exit");

    public MainMenuView(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new java.awt.Color(20, 17, 99));
        setPreferredSize(new java.awt.Dimension(400, 400));

        newGameButton.setAlignmentX(CENTER_ALIGNMENT);
        highScoresButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(newGameButton);
        add(Box.createVerticalStrut(10));
        add(highScoresButton);
        add(Box.createVerticalStrut(10));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    public void addNewGameButtonListener(ActionListener listener){
        newGameButton.addActionListener(listener);
    }
    public void addHighScoresButtonListener(ActionListener listener){
        highScoresButton.addActionListener(listener);
    }
    public void addExitButtonListener(ActionListener listener){
        exitButton.addActionListener(listener);
    }
}
