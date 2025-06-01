package Model;

import Controller.GameController;

public class PacManModel {
    private int x, y;
    private Direction direction = Direction.NONE;
    private final int tileSize;
    private final MapModel mapModel;
    private final GameController gameController;

    public PacManModel(int startX, int startY, int tileSize, MapModel mapModel, GameController gameController) {
        this.x = startX;
        this.y = startY;
        this.tileSize = tileSize;
        this.mapModel = mapModel;
        this.gameController = gameController;
    }


    public void setDirection(Direction direction){
        this.direction = direction;
    }

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


            if(mapModel.getMap()[y][x] == 0){
                mapModel.clearDotAt(y,x);
                gameController.increaseScore();
            }



            System.out.println("PacMan at " + x + " " + y + " " + direction);
        }
    }

    private boolean canMove(int x, int y){

        // First check boundaries
        if (x < 0 || x >= mapModel.getMap()[0].length || y < 0 || y >= mapModel.getMap().length) {
            return false;
        }

        // Then check if tile is a wall (1-14)
        int tile = mapModel.getMap()[y][x];  // Note: typically map[y][x] for row-major order
        return tile < 1 || tile > 14;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getPixelX() { return x * tileSize; }
    public int getPixelY() { return y * tileSize; }
}
