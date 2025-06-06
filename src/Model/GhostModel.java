package Model;

import Controller.GameController;

public class GhostModel implements СreatureModel{
    private int x, y;
    private final int startX, startY;
    private Direction direction = Direction.NONE;
    private final int tileSize;
    private final MapModel mapModel; // not final for map regeneration ability
    private final String color;
    private volatile boolean frozen = false;
    private volatile int movementDelay = 300;


    private final double aggressionFactor;

    public GhostModel(int startX,
                      int startY,
                      int tileSize,
                      MapModel mapModel,
                      GameController gameController,
                      double aggressionFactor,
                      String color) {
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;

        this.tileSize = tileSize;
        this.mapModel = mapModel;
        this.aggressionFactor = aggressionFactor;
        this.color = color;
    }

    public int[][] getMap() {
        return mapModel.getMap();
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
            int oldX = x;
            int oldY = y;

            x = newX;
            y = newY;




            System.out.println("Ghost at " + x + " " + y + " " + direction);
        }
    }

    @Override
    public boolean canMove(int x, int y){
        // First check boundaries
        if (x < 0 || x >= mapModel.getMap()[0].length || y < 0 || y >= mapModel.getMap().length) {
            return false;
        }

        // Then check if tile is a wall (1-14)
        int tile = mapModel.getMap()[y][x];
        return tile != 1;
    }

    public double getAggressionFactor() {
        return aggressionFactor;
    }
    @Override
    public void resetPosition(){
        x = startX;
        y = startY;
        this.direction = Direction.NONE;
    }

    @Override
    public int getX() { return x; }
    @Override
    public int getY() { return y; }
    @Override
    public int getPixelX() { return x * tileSize; }
    @Override
    public int getPixelY() { return y * tileSize; }
    @Override
    public int getTileSize(){
        return tileSize;
    }

    public String getColor() {
        return color;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public int getMovementDelay() {
        return movementDelay;
    }

    public void setMovementDelay(int movementDelay) {
        this.movementDelay = movementDelay;
    }
}
