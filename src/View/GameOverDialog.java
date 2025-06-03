package View;

import javax.swing.*;
import java.awt.*;

public class GameOverDialog extends JDialog {
    private boolean confirmed = false;
    private String playerName;

    public GameOverDialog(JFrame parent, int score) {
        super(parent, "Game Over", true);
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        // Title
        JLabel title = new JLabel("Game Over!", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.RED);
        title.setBounds(0, 30, 400, 40);
        add(title);

        // Prompt
        JLabel prompt = new JLabel("Enter your name:", SwingConstants.CENTER);
        prompt.setFont(new Font("Arial", Font.PLAIN, 16));
        prompt.setForeground(Color.WHITE);
        prompt.setBounds(0, 80, 400, 30);
        add(prompt);

        // Input field
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 120, 200, 30);
        add(nameField);

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
        add(submitBtn);
    }

    public boolean isConfirmed(){
        return confirmed;
    }

    public String getPlayerName(){
        return playerName;
    }
}
