import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
    private CardLayout cardLayout;

    public ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }

    public MainMenu(CardLayout cardLayout, JPanel cardPanel){
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(100,100,100,100));



        ImageIcon newGameNormal = iconGenerate("icons/Buttons/NewGameNormal.png", 150,75);
        ImageIcon newGameHover = iconGenerate("icons/Buttons/NewGameHover.png",150,75);
        ImageIcon scoresNormal = iconGenerate("icons/Buttons/ScoresNormal.png",150,75);
        ImageIcon scoresHover = iconGenerate("icons/Buttons/ScoresHover.png",150,75);
        ImageIcon exitNormal = iconGenerate("icons/Buttons/ExitNormal.png",150,75);
        ImageIcon exitHover = iconGenerate("icons/Buttons/ExitHover.png",150,75);
        ImageIcon mainLogo = iconGenerate("icons/Logos/MainLogo.png", 200,200);
        ImageIcon mainLogoText = iconGenerate("icons/Logos/LogoText.png", 250,50);


        JButton startButton = new JButton(newGameNormal);
        JButton scoresButton = new JButton(scoresNormal);
        JButton exitButton = new JButton(exitNormal);
        JLabel logo = new JLabel();
        JLabel logoText = new JLabel();
        logo.setIcon(mainLogo);
        logoText.setIcon(mainLogoText);

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoText.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        scoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.setRolloverIcon(newGameHover);
        scoresButton.setRolloverIcon(scoresHover);
        exitButton.setRolloverIcon(exitHover);

        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);
        startButton.setBorder(null);

        scoresButton.setContentAreaFilled(false);
        scoresButton.setBorderPainted(false);
        scoresButton.setBorder(null);

        exitButton.setContentAreaFilled(false);
        exitButton.setBorderPainted(false);
        exitButton.setBorder(null);

        add(Box.createVerticalGlue());
        add(logo);
        add(Box.createVerticalStrut(20));
        add(logoText);
        add(Box.createVerticalStrut(20));
        add(startButton);
        add(Box.createVerticalStrut(20));
        add(scoresButton);
        add(Box.createVerticalStrut(20));
        add(exitButton);
        add(Box.createVerticalGlue());


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cardPanel, "Size chooser");
                cardPanel.getComponent(1).requestFocusInWindow();

            }
        });

        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cardLayout.show(cardPanel, "Scores");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });


        add(Box.createVerticalGlue());
        add(startButton);
        add(Box.createHorizontalStrut(20));
        add(scoresButton);
        add(Box.createHorizontalStrut(20));
        add(exitButton);
        add(Box.createHorizontalStrut(20));
        add(Box.createVerticalGlue());



    }
}
