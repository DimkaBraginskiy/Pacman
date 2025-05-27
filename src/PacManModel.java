public class PacManModel {
    private int x, y;
    private Direction direction = Direction.NONE;
    private final int tileSize;
    private final int [][]map;

    public PacManModel(int startX, int startY, int tileSize, int[][] map) {
        this.x = startX;
        this.y = startY;
        this.tileSize = tileSize;
        this.map = map;
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
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case RIGHT:
                newX++;
                break;
            case LEFT:
                newX--;
                break;
        }

        if(canMove(newX, newY)){
            x = newX;
            y = newY;
            System.out.println("PacMan at " + x + " " + y);
        }
    }

    private boolean canMove(int x, int y){
        if(x < 0 || x >= map[0].length - 2 || y < 0 || y >= map.length-2 ){
            return false;
        }

        return map[y][x] != 1; //horizontal wall check for now
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getPixelX() { return x * tileSize; }
    public int getPixelY() { return y * tileSize; }
}
