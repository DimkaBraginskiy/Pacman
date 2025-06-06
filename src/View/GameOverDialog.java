package View;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog {
    private boolean confirmed = false;
    private String playerName;

    public GameOverDialog(JFrame parent, int score, String titleText, String messageText, Color titleColor) {
        super(parent, "Game Over", true);
        setResizable(true);

        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel(titleText, SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(titleColor);
        title.setBounds(0, 30, 400, 40);
        add(title, BorderLayout.NORTH);

        // Center input panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.BLACK);

        // Prompt
        JLabel prompt = new JLabel(messageText, SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 16));
        prompt.setForeground(Color.WHITE);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input field
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(200, 30));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(prompt);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(nameField);
        centerPanel.add(Box.createVerticalStrut(20));

        add(centerPanel, BorderLayout.CENTER);

        // Submit button
        JButton submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 170, 100, 30);
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                playerName = name;
                confirmed = true;
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a name!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.BLACK);
        bottomPanel.add(submitBtn);
        add(bottomPanel, BorderLayout.SOUTH);


    }

    public boolean isConfirmed(){
        return confirmed;
    }

    public String getPlayerName(){
        return playerName;
    }
}
