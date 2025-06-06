package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomMapSizeDialog extends JDialog {
    private JTextField rowsField;
    private JTextField colsField;

    private int rows = -1;
    private int cols = -1;
    private int cellSize;
    private boolean confirmed = false;


    public CustomMapSizeDialog(JFrame parent){
        super(parent, "Custom Map Size Selection", true); // Parent, Title and Modal = True
        setSize(400, 250);
        setMinimumSize(new Dimension(350, 200));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.BLACK);

        // --- Top label ---
        JLabel topText = new JLabel("Enter Custom Map Size", SwingConstants.CENTER);
        topText.setForeground(Color.WHITE);
        topText.setFont(new Font("Arial", Font.BOLD, 18));
        add(topText, BorderLayout.NORTH);

        // --- Center input panel ---
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setOpaque(false);

        JLabel rowsLabel = new JLabel("Rows (10-100):", SwingConstants.RIGHT);
        rowsLabel.setForeground(Color.WHITE);
        rowsField = new JTextField("10");

        JLabel colsLabel = new JLabel("Columns (10-100):", SwingConstants.RIGHT);
        colsLabel.setForeground(Color.WHITE);
        colsField = new JTextField("10");

        inputPanel.add(rowsLabel);
        inputPanel.add(rowsField);
        inputPanel.add(colsLabel);
        inputPanel.add(colsField);

        add(inputPanel, BorderLayout.CENTER);

        // --- Bottom buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            try {
                rows = Integer.parseInt(rowsField.getText());
                cols = Integer.parseInt(colsField.getText());
                if (rows < 10 || rows > 100 || cols < 10 || cols > 100) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter values between 10 and 100.",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                cellSize = generateCellSize();
                confirmed = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Please enter valid integer numbers.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            confirmed = false;
            dispose();
        });

        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private int generateCellSize() {
        if (rows >= 10 && cols <= 20) return 45;
        if (rows > 20 && cols <= 30) return 30;
        if (rows > 30 && cols <= 40) return 20;
        if (rows > 40 && cols <= 50) return 15;
        if (rows > 50 && cols <= 60) return 12;
        if (rows > 60 && cols <= 70) return 10;
        return 8;
    }

    public int getRows() { return rows; }
    public int getCols() { return cols; }
    public boolean isConfirmed() { return confirmed; }
    public int getCellSize() { return cellSize; }
}
