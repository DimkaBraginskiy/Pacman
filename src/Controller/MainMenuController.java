package Controller;

import View.MainFrame;
import View.MainMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {

    public MainMenuController(MainFrame mainFrame) {
        MainMenu mainMenu = new MainMenu();

        mainFrame.addPanel("MainMenu", mainMenu);
        mainFrame.showPanel("MainMenu");

        mainMenu.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.showPanel("Size Chooser");
            }
        });

        mainMenu.scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.showPanel("Scores");
            }
        });

        mainMenu.exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
