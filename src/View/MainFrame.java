package View;

import javax.swing.*;
import java.awt.*;


public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;


    public MainFrame() {
        int windowWidth = 800;
        int windowHeight = 800;


        setTitle("Pac-Man");
        //setSize(windowWidth, windowHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel);
    }

    public void addPanel(String name, JPanel panel){
        cardPanel.add(panel, name);
    }

    public void showPanel(String name){
        cardLayout.show(cardPanel, name);
    }

    public JPanel getPanel(){
        return cardPanel;
    }
}