public class MapModel {
    private int rows;
    private int cols;
    private int[][] map;


    public MapModel(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.map = new int[rows][cols];
        generateMap();
    }


    public void generateMap(){

        map = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(i == 0 && j == 0){
                    map[i][j] = 3; // LeftUpClose
                }else if(i == 0 && j == cols-1){
                    map[i][j] = 4; // RightUpClose
                }else if(i == rows-1 && j == 0){
                    map[i][j] = 5; // LeftDownClose
                }else if(i == rows-1 && j == cols-1) {
                    map[i][j] = 6; // RightDownClose
                }else if( i == 0 && j > 0 && j < cols-1   ||   i == rows-1 && j > 0 && j < cols-1) {
                    map[i][j] = 1; // WallHorizontal
                }else if( i > 0 && j == 0 && i < rows-1   || i > 0 && j ==cols-1 && i < rows-1) {
                    map[i][j] = 2; // WallVertical
                }
            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
