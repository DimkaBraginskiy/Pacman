package Controller;

import View.CustomMapSizeDialog;
import View.MainFrame;
import View.MapSizeChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapSizeChooserController {

    public MapSizeChooserController(MainFrame mainFrame) {
        MapSizeChooser mapSizeChooser = new MapSizeChooser();

        mainFrame.addPanel("Size Chooser", mapSizeChooser);
        mainFrame.showPanel("Size Chooser");

        mapSizeChooser.button10.addActionListener(e ->
                new GameController(mainFrame,10, 10, 45));

        mapSizeChooser.button12.addActionListener(e ->
                new GameController(mainFrame,12, 12, 45));

        mapSizeChooser.button16.addActionListener(e ->
                new GameController(mainFrame,16, 16, 45));

        mapSizeChooser.button20.addActionListener(e ->
                new GameController(mainFrame,20, 20, 30));

        mapSizeChooser.button24.addActionListener(e ->
                new GameController(mainFrame,24, 24, 30));

        mapSizeChooser.buttonCustom.addActionListener(e -> {
            CustomMapSizeDialog dialog = new CustomMapSizeDialog(mainFrame);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                new GameController(mainFrame, dialog.getRows(), dialog.getCols(), dialog.getCellSize());
            }
        });
    }
}
