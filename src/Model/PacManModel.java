package Model;

import Controller.GameController;

public class PacManModel implements СreatureModel{
    private int x, y;
    private final int startX, startY;
    private Direction direction = Direction.NONE;
    private final int tileSize;
    private final MapModel mapModel;
    private final GameController gameController;
    private int lives = 3;

    public PacManModel(int startX, int startY, int tileSize, MapModel mapModel, GameController gameController) {
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;

        this.tileSize = tileSize;
        this.mapModel = mapModel;
        this.gameController = gameController;
        mapModel.setPacManPosition(x, y);
    }

    @Override
    public void setDirection(Direction direction){
        this.direction = direction;
    }
    @Override
    public void move(){
        if(direction == Direction.NONE){
            return;
        }

        int newX = x;
        int newY = y;

        switch (direction){
            case Direction.UP:
                newY--;
                break;
            case Direction.DOWN:
                newY++;
                break;
            case Direction.RIGHT:
                newX++;
                break;
            case Direction.LEFT:
                newX--;
                break;
        }

        if(canMove(newX, newY)){
            x = newX;
            y = newY;

            mapModel.setPacManPosition(x, y);

            if(mapModel.getMap()[y][x] == 0){ // small or big dot checking
                mapModel.clearDotAt(y,x);
                gameController.increaseScore();
            }

            if(mapModel.getMap()[y][x] == 15){ // small or big dot checking
                mapModel.clearDotAt(y,x);
                gameController.increaseScore();
                gameController.activateEatingMode();
            }

            System.out.println("PacMan at " + x + " " + y + " " + direction);
        }
    }
    @Override
    public boolean canMove(int x, int y) {
        if (x < 0 || x >= mapModel.getMap()[0].length || y < 0 || y >= mapModel.getMap().length) {
            return false;
        }

        int tile = mapModel.getMap()[y][x];
        return tile != 1; // only block walls
    }

    public int getLives() {
        return lives;
    }

    public void decreaseLife() {
        this.lives--;
    }
    @Override
    public void resetPosition(){
        x = startX;
        y = startY;
        this.direction = Direction.NONE;
        mapModel.setPacManPosition(x, y);
    }
    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }
    @Override
    public int getPixelX() { return x * tileSize; }
    @Override
    public int getPixelY() { return y * tileSize; }
}
