import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class MapSizeChooser extends JPanel {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public ImageIcon iconGenerate(String path, int sizeX, int sizeY) {
        ImageIcon icon = new ImageIcon(path);
        Image originImage = icon.getImage();
        Image scaledImage = originImage.getScaledInstance(sizeX, sizeY, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon();
        scaledIcon.setImage(scaledImage);
        return scaledIcon;
    }



    public MapSizeChooser(CardLayout cardLayout, JPanel cardPanel, MainWindow mainWindow){
        this.cardLayout = cardLayout;
        this.cardPanel = cardPanel;

        setLayout(null);
        setBackground(Color.BLACK);


        MapSizeActionHandler handler = new MapSizeActionHandler(mainWindow);

        ImageIcon selectMapText = iconGenerate("icons/Buttons/SelectMapText.png",300,100);
        ImageIcon Map12TextNormal = iconGenerate("icons/Buttons/12x12Normal.png",150,40);
        ImageIcon Map12TextHover = iconGenerate("icons/Buttons/12x12Hover.png",150,40);

        ImageIcon Map16TextNormal = iconGenerate("icons/Buttons/16x16Normal.png",150,40);
        ImageIcon Map16TextHover = iconGenerate("icons/Buttons/16x16Hover.png",150,40);

        ImageIcon Map10TextNormal = iconGenerate("icons/Buttons/10x10Normal.png",150,40);
        ImageIcon Map10TextHover = iconGenerate("icons/Buttons/10x10Hover.png",150,40);

        ImageIcon Map20TextNormal = iconGenerate("icons/Buttons/20x20Normal.png",150,40);
        ImageIcon Map20TextHover = iconGenerate("icons/Buttons/20x20Hover.png",150,40);

        ImageIcon Map24TextNormal = iconGenerate("icons/Buttons/24x24Normal.png",150,40);
        ImageIcon Map24TextHover = iconGenerate("icons/Buttons/24x24Hover.png",150,40);

        ImageIcon CustomButtonNormal = iconGenerate("icons/Buttons/CustomSizeButtonNormal.png",200,60);
        ImageIcon CustomButtonHover = iconGenerate("icons/Buttons/CustomSizeButtonHover.png",200,60);


        JLabel topText = new JLabel(selectMapText);
        topText.setBounds(250,20,300,100);
        topText.setAlignmentX(CENTER_ALIGNMENT);
        add(topText);



        JButton button12 = new JButton(Map12TextNormal);
        button12.setBounds(300, 100, 200, 50);
        button12.setAlignmentX(CENTER_ALIGNMENT);
        add(button12);
        button12.setContentAreaFilled(false);
        button12.setBorderPainted(false);
        button12.setBorder(null);



        JButton button16 = new JButton(Map16TextNormal);
        button16.setBounds(300, 150, 200, 50);
        button12.setAlignmentX(CENTER_ALIGNMENT);
        add(button16);
        button16.setContentAreaFilled(false);
        button16.setBorderPainted(false);
        button16.setBorder(null);


        JButton button10 = new JButton(Map10TextNormal);
        button10.setBounds(300, 200, 200, 50);
        button10.setAlignmentX(CENTER_ALIGNMENT);
        add(button10);
        button10.setContentAreaFilled(false);
        button10.setBorderPainted(false);
        button10.setBorder(null);


        JButton button20 = new JButton(Map20TextNormal);
        button20.setBounds(300, 250, 200, 50);
        button20.setAlignmentX(CENTER_ALIGNMENT);
        add(button20);
        button20.setContentAreaFilled(false);
        button20.setBorderPainted(false);
        button20.setBorder(null);


        JButton button24 = new JButton(Map24TextNormal);
        button24.setBounds(300, 300, 200, 50);
        button24.setAlignmentX(CENTER_ALIGNMENT);
        add(button24);
        button24.setContentAreaFilled(false);
        button24.setBorderPainted(false);
        button24.setBorder(null);

        JButton buttonCustomSize = new JButton(CustomButtonNormal);
        buttonCustomSize.setBounds(300, 350, 200, 50);
        buttonCustomSize.setAlignmentX(CENTER_ALIGNMENT);
        add(buttonCustomSize);
        buttonCustomSize.setContentAreaFilled(false);
        buttonCustomSize.setBorderPainted(false);
        buttonCustomSize.setBorder(null);

        button12.setRolloverIcon(Map12TextHover);
        button16.setRolloverIcon(Map16TextHover);
        button10.setRolloverIcon(Map10TextHover);
        button20.setRolloverIcon(Map20TextHover);
        button24.setRolloverIcon(Map24TextHover);
        buttonCustomSize.setRolloverIcon(CustomButtonHover);



        button10.addActionListener(handler.getFixedSizeListener(10, 10, 45));
        button12.addActionListener(handler.getFixedSizeListener(12, 12, 45));
        button16.addActionListener(handler.getFixedSizeListener(16, 16, 45));
        button20.addActionListener(handler.getFixedSizeListener(20, 20, 30));
        button24.addActionListener(handler.getFixedSizeListener(24, 24, 30));
        buttonCustomSize.addActionListener(handler.getCustomSizeListener());
    }


}
