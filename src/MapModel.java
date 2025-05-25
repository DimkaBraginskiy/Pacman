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


                //injectBlockAtBottomLeft(figuresBlock1, rows / 2-6 , cols / 2+6);
//                int rowPoint = rows / 2 - 6;
//                for(int point = cols/2 -10; point <= cols/2 +6; point+=5){
//                    injectBiggestFittingBlock(rowPoint, point);
//                }

                int rowPoint = rows / 2 - 6;
                int startCol = cols / 2 - 10;
                int endCol = cols / 2 + 6;
                int step = 5;

                int usableRows = rows - 4; // Exclude border/dot margins
                int middleCol = cols / 2;  // This is the vertical center


                for (int point = startCol; point <= endCol; point += step) {

                    if (usableRows % 2 == 0 && Math.abs(point - middleCol) < step / 2) {
                        // This column is approximately the center
                        injectBlockIfFits(figuresBlock5x4, rowPoint, point);
                    } else {
                        injectBiggestFittingBlock(rowPoint, point);
                    }
                }


                //injectBiggestFittingBlock(rows / 2 - 6, cols / 2 + 6);



            }
        }
    }

    private void injectBlockIfFits(int[][] block, int anchorRow, int anchorCol) {
        if (canFitBlock(block, anchorRow, anchorCol)) {
            injectBlockAtBottomLeft(block, anchorRow, anchorCol);
        } else {
            System.out.println("Specific block did not fit at row=" + anchorRow + " col=" + anchorCol);
        }
    }


    private void injectBiggestFittingBlock(int anchorRow, int anchorCol) {
        // Try the biggest blocks first
        if (canFitBlock(figuresBlock4x4, anchorRow, anchorCol)) {
            injectBlockAtBottomLeft(figuresBlock4x4, anchorRow, anchorCol);
        } else if (canFitBlock(figuresBlock3x3, anchorRow, anchorCol)) {
            injectBlockAtBottomLeft(figuresBlock3x3, anchorRow, anchorCol);
        }else if (canFitBlock(figuresBlock2x2, anchorRow, anchorCol)) {
            injectBlockAtBottomLeft(figuresBlock2x2, anchorRow, anchorCol);
        } else {
            System.out.println("No fitting block found at row=" + anchorRow + " col=" + anchorCol);
        }
    }



    private void injectBlockAtBottomLeft(int[][] block, int anchorRow, int anchorCol) {
        int blockHeight = block.length;
        int blockWidth = block[0].length;

        for (int i = 0; i < blockHeight; i++) {
            for (int j = 0; j < blockWidth; j++) {
                int mapRow = anchorRow - (blockHeight - 1 - i); // move up
                int mapCol = anchorCol + j; // move right

                // Bounds check
                if (mapRow >= 0 && mapRow < rows-2 && mapCol >= 0 && mapCol < cols-2) {
                    map[mapRow][mapCol] = block[i][j];
                }
            }
        }
    }

    private boolean canFitBlock(int[][] block, int anchorRow, int anchorCol) {
        int blockHeight = block.length;
        int blockWidth = block[0].length;

        // Expand area to check for margin (1-tile around)
        for (int i = -1; i <= blockHeight; i++) {
            for (int j = -1; j <= blockWidth; j++) {
                int mapRow = anchorRow - (blockHeight - 1 - i); // move up
                int mapCol = anchorCol + j; // move right

                // Check map bounds
                if (mapRow < 0 || mapRow >= rows - 2 || mapCol < 0 || mapCol >= cols - 2) {
                    return false; // Out of bounds
                }

                if (i >= 0 && i < blockHeight && j >= 0 && j < blockWidth) {
                    // inside the block
                    if (block[i][j] != 0 && map[mapRow][mapCol] != 0) {
                        return false; // Block overlaps existing
                    }
                } else {
                    // surrounding margin
                    if (map[mapRow][mapCol] != 0) {
                        return false; // margin must be empty too
                    }
                }
            }
        }

        return true;
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
