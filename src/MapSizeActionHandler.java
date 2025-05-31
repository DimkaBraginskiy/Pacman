import View.MainFrame;

import java.awt.event.ActionListener;

public class MapSizeActionHandler {
    private final MainFrame mainWindow;

    public MapSizeActionHandler(MainFrame mainWindow) {
        this.mainWindow = mainWindow;
    }

//    public ActionListener getFixedSizeListener(int rows, int cols, int cellSize) {
//        return e -> mainWindow.showGamePanel(rows, cols, cellSize);
//    }
//
//    public ActionListener getCustomSizeListener() {
//        return e -> {
//            View.CustomMapSizeDialog dialog = new View.CustomMapSizeDialog(mainWindow);
//            dialog.setVisible(true);
//            if (dialog.isConfirmed()) {
//                mainWindow.showGamePanel(dialog.getRows(), dialog.getCols(), dialog.getCellSize());
//            }
//        };
//    }
}