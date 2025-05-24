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
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);

        //Top Text:
        JLabel topText = new JLabel("Enter Custom Map Size", SwingConstants.CENTER);
        topText.setBounds(0, 20, 400, 30);
        topText.setForeground(Color.WHITE);
        add(topText);


        //Rows input:
        JLabel rowsLabel = new JLabel("Rows (10-100):");
        rowsLabel.setBounds(50, 80, 150, 30);
        rowsLabel.setForeground(Color.WHITE);
        add(rowsLabel);

        rowsField = new JTextField("10");
        rowsField.setBounds(200, 70, 150, 30);
        add(rowsField);



        //Columns input:
        JLabel colsLabel = new JLabel("Columns (10-100):");
        colsLabel.setBounds(50, 120, 150, 30);
        colsLabel.setForeground(Color.WHITE);
        add(colsLabel);

        colsField = new JTextField("10");
        colsField.setBounds(200, 120, 150, 30);
        add(colsField);

        //OK Button:
        JButton okButton = new JButton("OK");
        okButton.setBounds(100, 200, 80, 30);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    rows = Integer.parseInt(rowsField.getText());
                    cols = Integer.parseInt(colsField.getText());

                    if (rows < 10 || rows > 100 || cols < 10 || cols > 100) {

                        JOptionPane.showMessageDialog(CustomMapSizeDialog.this,
                                "Please enter rows and columns sizes only within specified ranges",
                                "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    cellSize = generateCellSize();
                    confirmed = true;
                    dispose(); // closes the modal window while keeping the main window active....

                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(CustomMapSizeDialog.this,
                            "Please enter valid numbers for rows and columns",
                            "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        add(okButton);

        //Cancel Button:
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(220, 200, 80, 30);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = false;
                dispose(); // closes the modal window while keeping the main window active....
            }
        });

        add(cancelButton);
    }

    //generating cell sizes so the window will fit into the screen:
    private int generateCellSize() {
        if (rows >= 10 && cols <= 20) {
            return 45;

        } else if (rows > 20 && cols <= 30) {
            return 30;

        }else if (rows > 30 && cols <= 40) {
            return 20;

        }else if (rows > 40 && cols <= 50) {
            return 15;
        } else if (rows > 50 && cols <= 60) {
            return 12;
        }else if (rows > 60 && cols <= 70) {
            return 10;
        } else {
            return 8; // the smallest size to fit...
        }
    }

    public int getRows() {
        return rows;
    }
    public int getCols() {
        return cols;
    }
    public boolean isConfirmed() {
        return confirmed;
    }
    public int getCellSize() {
        return cellSize;
    }






}
