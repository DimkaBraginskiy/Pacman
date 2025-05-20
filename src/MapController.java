public class MapController {
    private int width;
    private int height;


    public MapController(int width, int height){
        this.width = width;
        this.height = height;
    }


    public int[][] generateMap(){
        int[][] map = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                map[i][j] = 0;
            }
        }
        return map;
    }
}
