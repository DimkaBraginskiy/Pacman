import javax.swing.*;
import java.awt.*;

public class PacMan extends JLabel {
    private final int tileSize;
    private final int[][] map;
    private final GamePanel gamePanel;

    // Animation properties
    private final ImageIcon[] framesUp;
    private final ImageIcon[] framesDown;
    private final ImageIcon[] framesLeft;
    private final ImageIcon[] framesRight;
    private int currentFrame = 0;

    // Movement properties
    private Direction currentDirection = Direction.NONE;
    private Direction nextDirection = Direction.NONE;
    private int gridX, gridY;
    private int pixelX, pixelY;
    private boolean isMoving = false;

    // Speed control
    private int normalSpeed = 50;
    private int currentSpeed = normalSpeed;

    public PacMan(GamePanel gamePanel, int[][] map, int tileSize, int startX, int startY) {
        this.gamePanel = gamePanel;
        this.map = map;
        this.tileSize = tileSize;
        this.gridX = startX;
        this.gridY = startY;
        this.pixelX = gridX * tileSize;
        this.pixelY = gridY * tileSize;

        // Load animation frames
        framesRight = loadAnimationFrames("icons/PacManRight/PacMan", 3, "Right");
        framesLeft = loadAnimationFrames("PacManLeft/PacMan", 3, "Left");
        framesUp = loadAnimationFrames("PacManUp/PacMan", 3, "Up");
        framesDown = loadAnimationFrames("PacManDown/PacMan", 3, "Down");

        setIcon(framesRight[0]);
        setBounds(pixelX, pixelY, tileSize, tileSize);

        // Start animation thread
        new Thread(this::animate).start();
        // Start movement thread
        new Thread(this::move).start();
    }

    private ImageIcon[] loadAnimationFrames(String basePath, int frameCount, String direction) {
        ImageIcon[] frames = new ImageIcon[frameCount];
        for (int i = 0; i < frameCount; i++) {
            String path = basePath + (i + 1) + ".png";
            System.out.println("Loading frame: " + path); // Debugging output
            frames[i] = iconGenerate(path, tileSize, tileSize);
        }
        return frames;
    }

    public void setDirection(Direction direction) {
        this.nextDirection = direction;
        if (currentDirection == Direction.NONE) {
            currentDirection = nextDirection;
        }
    }

    private void animate() {
        while (true) {
            if (currentDirection != Direction.NONE && isMoving) {
                SwingUtilities.invokeLater(() -> {
                    switch (currentDirection) {
                        case UP: setIcon(framesUp[currentFrame]); break;
                        case DOWN: setIcon(framesDown[currentFrame]); break;
                        case LEFT: setIcon(framesLeft[currentFrame]); break;
                        case RIGHT: setIcon(framesRight[currentFrame]); break;
                    }
                    currentFrame = (currentFrame + 1) % framesRight.length;
                });
            }
            try {
                Thread.sleep(150); // Animation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void move() {
        while (true) {
            if (currentDirection != Direction.NONE) {
                isMoving = true;

                // Check if we can change direction
                if (nextDirection != currentDirection && canMove(nextDirection)) {
                    currentDirection = nextDirection;
                }

                // Calculate next position
                int nextGridX = gridX;
                int nextGridY = gridY;

                switch (currentDirection) {
                    case UP: nextGridY--; break;
                    case DOWN: nextGridY++; break;
                    case LEFT: nextGridX--; break;
                    case RIGHT: nextGridX++; break;
                }

                // Move if possible
                if (canMove(currentDirection)) {
                    // Update grid position
                    gridX = nextGridX;
                    gridY = nextGridY;

                    // Update pixel position smoothly
                    for (int i = 0; i < tileSize; i += 4) { // 4px per step
                        switch (currentDirection) {
                            case UP: pixelY--; break;
                            case DOWN: pixelY++; break;
                            case LEFT: pixelX--; break;
                            case RIGHT: pixelX++; break;
                        }

                        SwingUtilities.invokeLater(() -> {
                            setLocation(pixelX, pixelY);

                            // Check if we're centered on a tile
                            if (pixelX % tileSize == 0 && pixelY % tileSize == 0) {
                                handleTileAction();
                            }
                        });

                        try {
                            Thread.sleep(currentSpeed / tileSize);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    isMoving = false;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean canMove(Direction direction) {
        int nextX = gridX;
        int nextY = gridY;

        switch (direction) {
            case UP: nextY--; break;
            case DOWN: nextY++; break;
            case LEFT: nextX--; break;
            case RIGHT: nextX++; break;
        }

        // Check boundaries
        if (nextX < 0 || nextX >= map[0].length || nextY < 0 || nextY >= map.length) {
            return false;
        }

        // Check if not a wall (1) or special tiles that allow movement
        return map[nextY][nextX] != 1;
    }

    private void handleTileAction() {
        // Check if on dot (0)
        if (map[gridY][gridX] == 0) {
            map[gridY][gridX] = -1; // Mark as eaten
            gamePanel.updateDot(gridX, gridY);
        }
        // Handle special tiles (11-14) if needed
    }

    private ImageIcon iconGenerate(String path, int width, int height) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }
}