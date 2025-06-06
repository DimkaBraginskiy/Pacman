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
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 0, 15, 0); // space between components

        JLabel logo = new JLabel(iconGenerate("icons/Logos/MainLogo.png", 200, 200));
        JLabel logoText = new JLabel(iconGenerate("icons/Logos/LogoText.png", 250, 50));

        startButton = generateButton("icons/Buttons/NewGameNormal.png", "icons/Buttons/NewGameHover.png");
        scoresButton = generateButton("icons/Buttons/ScoresNormal.png", "icons/Buttons/ScoresHover.png");
        exitButton = generateButton("icons/Buttons/ExitNormal.png", "icons/Buttons/ExitHover.png");

        int row = 0;
        gbc.gridy = row++;
        add(logo, gbc);

        gbc.gridy = row++;
        add(logoText, gbc);

        gbc.gridy = row++;
        add(startButton, gbc);

        gbc.gridy = row++;
        add(scoresButton, gbc);

        gbc.gridy = row++;
        add(exitButton, gbc);
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

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY) {
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

}
