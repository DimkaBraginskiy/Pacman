package Model;

public class MapModel {
    private int rows;
    private int cols;
    private int[][] map;

    int minRow = 1;
    int maxRow = rows - 2;
    int minCol = 1;
    int maxCol = cols - 2;


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
                //TRI-WALLS:
                if(rows%2 ==0){
                    addTriWall(rows / 2-3, cols / 2-5, "left");
                    addTriWall(rows / 2-3, cols / 2+4, "right");

                    addTriWall(rows / 2+3, cols / 2-5, "left");
                    addTriWall(rows / 2+3, cols / 2+4, "right");
                }else{
                    addTriWall(rows / 2-3, cols / 2-4, "left");
                    addTriWall(rows / 2-3, cols / 2+4, "right");

                    addTriWall(rows / 2+3, cols / 2-4, "left");
                    addTriWall(rows / 2+3, cols / 2+4, "right");
                }







            }
        }
    }


    private void addTriWall(int centerRow, int centerCol, String direction) {
        switch (direction.toLowerCase()) {
            case "left":
                map[centerRow][centerCol] = 11;
                map[centerRow][centerCol + 1] = 7;     // leftClosed
                map[centerRow - 1][centerCol] = 9;     // topWall
                map[centerRow + 1][centerCol] = 10;    // bottomWall
                break;
            case "right":
                map[centerRow][centerCol] = 12;
                map[centerRow][centerCol - 1] = 8;     // rightClosed
                map[centerRow - 1][centerCol] = 9;
                map[centerRow + 1][centerCol] = 10;
                break;
            case "up":
                map[centerRow][centerCol] = 13;
                map[centerRow + 1][centerCol] = 10;    // bottomWall
                map[centerRow][centerCol - 1] = 7;     // leftWall
                map[centerRow][centerCol + 1] = 8;     // rightWall
                break;
            case "down":
                map[centerRow][centerCol] = 14;
                map[centerRow - 1][centerCol] = 9;     // topWall
                map[centerRow][centerCol - 1] = 7;     // leftWall
                map[centerRow][centerCol + 1] = 8;     // rightWall
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }


    public int[][] getMap() {
        return map;
    }

    private int[][] figuresBlock2x2 = {
            {-1, -1},
            {-1, -1}
    };

    private int[][] figuresBlock3x3 = {
            {-1, -1, -1},
            {-1, -1, -1},
            {-1, -1, -1}
    };

    private int[][] figuresBlock4x4 = {
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1}
    };

    private int[][] figuresBlock5x4 = {
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1}
    };



//    private int[][] figuresBlock5x5 = {
//            {-1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1}
//    };
//
//    private int[][] figuresBlock6x6 = {
//            {-1, -1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1, -1},
//            {-1, -1, -1, -1, -1, -1}
//    };
}
