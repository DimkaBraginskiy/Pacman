package View;

import javax.swing.*;
import java.awt.*;


public class MapSizeChooser extends JPanel {
    public JButton button10, button12, button16, button20, button24, buttonCustom;
    private JLabel topText;


    public MapSizeChooser(){
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



        topText = new JLabel(iconGenerate("icons/Buttons/SelectMapText.png", 300, 100));
        topText.setAlignmentX(CENTER_ALIGNMENT);
        add(topText);
        add(Box.createVerticalStrut(20));

        button10 = generateButton("icons/Buttons/10x10Hover.png","icons/Buttons/10x10Normal.png");
        button12 = generateButton("icons/Buttons/12x12Hover.png","icons/Buttons/12x12Normal.png");
        button16 = generateButton("icons/Buttons/16x16Hover.png","icons/Buttons/16x16Normal.png");
        button20 = generateButton("icons/Buttons/20x20Hover.png","icons/Buttons/20x20Normal.png");
        button24 = generateButton("icons/Buttons/24x24Hover.png","icons/Buttons/24x24Normal.png");
        buttonCustom = generateButton("icons/Buttons/CustomSizeButtonHover.png","icons/Buttons/CustomSizeButtonNormal.png");

        button10.setAlignmentX(Component.CENTER_ALIGNMENT);
        button12.setAlignmentX(Component.CENTER_ALIGNMENT);
        button16.setAlignmentX(Component.CENTER_ALIGNMENT);
        button20.setAlignmentX(Component.CENTER_ALIGNMENT);
        button24.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonCustom.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(button10);
        add(Box.createVerticalStrut(10));
        add(button12);
        add(Box.createVerticalStrut(10));
        add(button16);
        add(Box.createVerticalStrut(10));
        add(button20);
        add(Box.createVerticalStrut(10));
        add(button24);
        add(Box.createVerticalStrut(10));
        add(buttonCustom);
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

    private ImageIcon iconGenerate(String path, int sizeX, int sizeY){
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }




}
