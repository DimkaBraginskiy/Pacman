package Model;

import Controller.GameController;

public class GhostModel {
    private int x, y;
    private final int startX, startY;
    private Direction direction = Direction.NONE;
    private final int tileSize;
    private final MapModel mapModel;


    private final double aggressionFactor;

    public GhostModel(int startX, int startY, int tileSize, MapModel mapModel, GameController gameController, double aggressionFactor) {
        this.x = startX;
        this.y = startY;
        this.startX = startX;
        this.startY = startY;

        this.tileSize = tileSize;
        this.mapModel = mapModel;
        this.aggressionFactor = aggressionFactor;
    }

    public int[][] getMap() {
        return mapModel.getMap();
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


            System.out.println("Ghost at " + x + " " + y + " " + direction);
        }
    }

    public boolean canMove(int x, int y){
        // First check boundaries
        if (x < 0 || x >= mapModel.getMap()[0].length || y < 0 || y >= mapModel.getMap().length) {
            return false;
        }

        // Then check if tile is a wall (1-14)
        int tile = mapModel.getMap()[y][x];
        return tile < 1 || tile > 14;
    }

    public double getAggressionFactor() {
        return aggressionFactor;
    }

    public void resetPosition(){
        x = startX;
        y = startY;
        this.direction = Direction.NONE;
    }


    public int getX() { return x; }
    public int getY() { return y; }
    public int getPixelX() { return x * tileSize; }
    public int getPixelY() { return y * tileSize; }

}
