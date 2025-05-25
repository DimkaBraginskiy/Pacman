import java.awt.event.ActionListener;

public class MapSizeActionHandler {
    private final MainWindow mainWindow;

    public MapSizeActionHandler(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    public ActionListener getFixedSizeListener(int rows, int cols, int cellSize) {
        return e -> mainWindow.showGamePanel(rows, cols, cellSize);
    }

    public ActionListener getCustomSizeListener() {
        return e -> {
            CustomMapSizeDialog dialog = new CustomMapSizeDialog(mainWindow);
            dialog.setVisible(true);
            if (dialog.isConfirmed()) {
                mainWindow.showGamePanel(dialog.getRows(), dialog.getCols(), dialog.getCellSize());
            }
        };
    }
}