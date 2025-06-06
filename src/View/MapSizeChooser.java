package View;

import javax.swing.*;
import java.awt.*;


public class MapSizeChooser extends JPanel {
    public JButton button10, button12, button16, button20, button24, buttonCustom;
    private JLabel topText;


    public MapSizeChooser(){
        setBackground(Color.BLACK);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0); // space between components

        int row = 0;

        JLabel topText = new JLabel(iconGenerate("icons/Buttons/SelectMapText.png", 300, 100));
        gbc.gridy = row++;
        add(topText, gbc);

        button10 = generateButton("icons/Buttons/10x10Hover.png", "icons/Buttons/10x10Normal.png");
        button12 = generateButton("icons/Buttons/12x12Hover.png", "icons/Buttons/12x12Normal.png");
        button16 = generateButton("icons/Buttons/16x16Hover.png", "icons/Buttons/16x16Normal.png");
        button20 = generateButton("icons/Buttons/20x20Hover.png", "icons/Buttons/20x20Normal.png");
        button24 = generateButton("icons/Buttons/24x24Hover.png", "icons/Buttons/24x24Normal.png");
        buttonCustom = generateButton("icons/Buttons/CustomSizeButtonHover.png", "icons/Buttons/CustomSizeButtonNormal.png");

        gbc.gridy = row++; add(button10, gbc);
        gbc.gridy = row++; add(button12, gbc);
        gbc.gridy = row++; add(button16, gbc);
        gbc.gridy = row++; add(button20, gbc);
        gbc.gridy = row++; add(button24, gbc);
        gbc.gridy = row++; add(buttonCustom, gbc);
    }

    private JButton generateButton(String iconPath, String hoverPath) {
        JButton button = new JButton(iconGenerate(iconPath, 180, 55));
        button.setRolloverIcon(iconGenerate(hoverPath, 180, 55));
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setBorder(null);
        return button;
    }

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY) {
        ImageIcon icon = new ImageIcon(path);
        Image scaledImage = icon.getImage().getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
