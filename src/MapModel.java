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
                //EDGE WALLS:
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


                //INNER GHOST SPAWN WALLS:
                //empty space inside of spawn:
                int innerCols = cols - 2;
                if((innerCols % 2) != 0){
                    map[rows/2][cols/2] = -1;
                    map[rows/2][cols/2-1] = -1;
                    map[rows/2][cols/2+1] = -1;

                    //walls around spawn:
                    map[rows/2+1][cols/2] = 1;
                    map[rows/2+1][cols/2+1] = 1;
                    map[rows/2+1][cols/2-1] = 1;

                    map[rows/2][cols/2+2] = 2;
                    map[rows/2][cols/2-2] = 2;

                    map[rows/2-1][cols/2-1] = 7;//leftClosed
                    map[rows/2-1][cols/2+1] = 8;//RightClosed

                    map[rows/2-1][cols/2-2] = 3;
                    map[rows/2-1][cols/2+2] = 4;
                    map[rows/2+1][cols/2-2] = 5;
                    map[rows/2+1][cols/2+2] = 6;

                }else if((innerCols%2)==0){
                    map[rows/2][cols/2] = -1;
                    map[rows/2][cols/2-1] = -1;
                    map[rows/2][cols/2-2] = -1;
                    map[rows/2][cols/2+1] = -1;


                    //walls around spawn:
                    map[rows/2+1][cols/2] = 1;
                    map[rows/2+1][cols/2+1] = 1;
                    map[rows/2+1][cols/2-1] = 1;
                    map[rows/2+1][cols/2-2] = 1;

                    map[rows/2][cols/2+2] = 2;
                    map[rows/2][cols/2-3] = 2;


                    map[rows/2-1][cols/2-2] = 7;//leftClosed
                    map[rows/2-1][cols/2+1] = 8;//RightClosed

                    map[rows/2-1][cols/2-3] = 3;
                    map[rows/2-1][cols/2+2] = 4;
                    map[rows/2+1][cols/2-3] = 5;
                    map[rows/2+1][cols/2+2] = 6;
                }







            }
        }
    }

    public int[][] getMap() {
        return map;
    }
}
