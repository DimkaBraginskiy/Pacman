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
            System.out.println("PacMan at " + x + " " + y + " " + direction);
        }
    }

    private boolean canMove(int x, int y){
        // First check boundaries
        if (x < 0 || x >= map[0].length || y < 0 || y >= map.length) {
            return false;
        }

        // Then check if tile is a wall (1-14)
        int tile = map[y][x];  // Note: typically map[y][x] for row-major order
        return tile < 1 || tile > 14;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getPixelX() { return x * tileSize; }
    public int getPixelY() { return y * tileSize; }
}
