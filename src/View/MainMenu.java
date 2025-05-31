package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private CardLayout cardLayout;
    public JButton startButton;
    public JButton scoresButton;
    public JButton exitButton;

    public MainMenu(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);
        setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        JLabel logo = new JLabel(iconGenerate("icons/Logos/MainLogo.png", 200, 200));
        JLabel logoText = new JLabel(iconGenerate("icons/Logos/LogoText.png", 250, 50));

        logo.setAlignmentX(CENTER_ALIGNMENT);
        logoText.setAlignmentX(CENTER_ALIGNMENT);

        startButton = generateButton("icons/Buttons/NewGameNormal.png", "icons/Buttons/NewGameHover.png");
        scoresButton = generateButton("icons/Buttons/ScoresNormal.png", "icons/Buttons/ScoresHover.png");
        exitButton = generateButton("icons/Buttons/ExitNormal.png", "icons/Buttons/ExitHover.png");

        startButton.setAlignmentX(CENTER_ALIGNMENT);
        scoresButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(logo);
        add(Box.createVerticalStrut(10));
        add(logoText);
        add(Box.createVerticalStrut(30));
        add(startButton);
        add(Box.createVerticalStrut(20));
        add(scoresButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    private JButton generateButton(String iconPath, String hoverPath) {
        JButton button = new JButton(iconGenerate(iconPath, 150, 75));
        button.setRolloverIcon(iconGenerate(hoverPath, 150, 75));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(null);

        return button;
    }

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }

}
