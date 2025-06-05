package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        //this.map = new int[rows][cols];
        //this.map = maze20x20;
        //generateMap();
        generateFromPredefinedMazeAndCrop(rows, cols);
    }

    public void generateFromPredefinedMazeAndCrop(int targetRows, int targetCols) {
        int baseRows = maze10x25.length;
        int baseCols = maze10x25[0].length;

        int repeatHorizontal = 100 / baseCols;
        int repeatVertical = 100 / baseRows;

        int[][] fullMaze = new int[baseRows * repeatVertical][baseCols * repeatHorizontal];

        // Step 1: Repeat predefined maze to build full 100x100 map
        for (int i = 0; i < repeatVertical; i++) {
            for (int j = 0; j < repeatHorizontal; j++) {
                for (int r = 0; r < baseRows; r++) {
                    for (int c = 0; c < baseCols; c++) {
                        fullMaze[i * baseRows + r][j * baseCols + c] = maze10x25[r][c];
                    }
                }
            }
        }

        // Step 2: Crop center
        int startRow = (fullMaze.length - targetRows) / 2;
        int startCol = (fullMaze[0].length - targetCols) / 2;

        int[][] cropped = new int[targetRows][targetCols];
        for (int i = 0; i < targetRows; i++) {
            for (int j = 0; j < targetCols; j++) {
                cropped[i][j] = fullMaze[startRow + i][startCol + j];
            }
        }

        // Step 3: Create framed map (walls + dots + cropped center)
        int framedRows = targetRows + 4;
        int framedCols = targetCols + 4;
        int[][] framedMap = new int[framedRows][framedCols];

        // Step 3.1: Fill all with walls
        for (int i = 0; i < framedRows; i++) {
            for (int j = 0; j < framedCols; j++) {
                framedMap[i][j] = 1; // wall
            }
        }

        // Step 3.2: Fill inner border with dots
        for (int i = 1; i < framedRows - 1; i++) {
            for (int j = 1; j < framedCols - 1; j++) {
                framedMap[i][j] = 2; // small dot
            }
        }

        // Step 3.3: Place cropped map inside center of frame
        for (int i = 0; i < targetRows; i++) {
            for (int j = 0; j < targetCols; j++) {
                framedMap[i + 2][j + 2] = cropped[i][j];
            }
        }

        this.map = framedMap;
        this.rows = framedRows;
        this.cols = framedCols;
    }



    public void generateMap(){

//        map = new int[rows][cols];
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < cols; j++) {
//                //EDGE WALLS:
//                if(i == 0 && j == 0){
//                    map[i][j] = 3; // LeftUpClose
//                }else if(i == 0 && j == cols-1){
//                    map[i][j] = 4; // RightUpClose
//                }else if(i == rows-1 && j == 0){
//                    map[i][j] = 5; // LeftDownClose
//                }else if(i == rows-1 && j == cols-1) {
//                    map[i][j] = 6; // RightDownClose
//                }else if( i == 0 && j > 0 && j < cols-1   ||   i == rows-1 && j > 0 && j < cols-1) {
//                    map[i][j] = 1; // WallHorizontal
//                }else if( i > 0 && j == 0 && i < rows-1   || i > 0 && j ==cols-1 && i < rows-1) {
//                    map[i][j] = 2; // WallVertical
//                }
//
//
//                //INNER GHOST SPAWN WALLS:
//                //empty space inside of spawn:
//                int innerCols = cols - 2;
//                if((innerCols % 2) != 0){
//                    map[rows/2][cols/2] = -1;
//                    map[rows/2][cols/2-1] = -1;
//                    map[rows/2][cols/2+1] = -1;
//
//                    //walls around spawn:
//                    map[rows/2+1][cols/2] = 1;
//                    map[rows/2+1][cols/2+1] = 1;
//                    map[rows/2+1][cols/2-1] = 1;
//
//                    map[rows/2][cols/2+2] = 2;
//                    map[rows/2][cols/2-2] = 2;
//
//                    map[rows/2-1][cols/2-1] = 7;//leftClosed
//                    map[rows/2-1][cols/2+1] = 8;//RightClosed
//
//                    map[rows/2-1][cols/2-2] = 3;
//                    map[rows/2-1][cols/2+2] = 4;
//                    map[rows/2+1][cols/2-2] = 5;
//                    map[rows/2+1][cols/2+2] = 6;
//
//                }else if((innerCols%2)==0){
//                    map[rows/2][cols/2] = -1;
//                    map[rows/2][cols/2-1] = -1;
//                    map[rows/2][cols/2-2] = -1;
//                    map[rows/2][cols/2+1] = -1;
//
//
//                    //walls around spawn:
//                    map[rows/2+1][cols/2] = 1;
//                    map[rows/2+1][cols/2+1] = 1;
//                    map[rows/2+1][cols/2-1] = 1;
//                    map[rows/2+1][cols/2-2] = 1;
//
//                    map[rows/2][cols/2+2] = 2;
//                    map[rows/2][cols/2-3] = 2;
//
//
//                    map[rows/2-1][cols/2-2] = 7;//leftClosed
//                    map[rows/2-1][cols/2+1] = 8;//RightClosed
//
//                    map[rows/2-1][cols/2-3] = 3;
//                    map[rows/2-1][cols/2+2] = 4;
//                    map[rows/2+1][cols/2-3] = 5;
//                    map[rows/2+1][cols/2+2] = 6;
//                }
//                //TRI-WALLS:
//                if(rows%2 ==0){
//                    addTriWall(rows / 2-3, cols / 2-5, "left");
//                    addTriWall(rows / 2-3, cols / 2+4, "right");
//
//                    addTriWall(rows / 2+3, cols / 2-5, "left");
//                    addTriWall(rows / 2+3, cols / 2+4, "right");
//                }else{
//                    addTriWall(rows / 2-3, cols / 2-4, "left");
//                    addTriWall(rows / 2-3, cols / 2+4, "right");
//
//                    addTriWall(rows / 2+3, cols / 2-4, "left");
//                    addTriWall(rows / 2+3, cols / 2+4, "right");
//                }
//
//
//
//
//
//
//
//            }
//        }
//
//        placeBigDots(4);

    }

    public int[][] maze10x25 = {
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2},
            {2,1,1,1,1,2,1,2,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,1,1},
            {2,1,2,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,2,2,2},
            {2,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1},
            {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,2,2,2,2},
            {2,1,2,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1,2,1},
            {2,1,2,2,1,2,1,2,2,2,2,2,2,2,2,2,2,2,1,2,1,2,1,2,1},
            {2,1,1,2,1,2,1,2,1,1,1,1,2,1,1,1,1,2,1,2,2,2,1,2,1},
            {2,1,2,2,1,2,1,2,2,2,2,1,2,1,2,2,2,2,1,2,1,2,1,2,1},
            {2,1,2,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,2,1,2,1}
};



    public void placeBigDots(int count){
        List<int[]> validPositions = new ArrayList<>();

        for(int row = 1; row < rows -1; row++){
            for(int col = 1; col < cols -1; col++){
                if(map[row][col] == 0 || map[row][col] == -1){
                    validPositions.add(new int[]{row, col});
                }
            }
        }

        if(validPositions.size() < count){
            count = validPositions.size();
        }

        Collections.shuffle(validPositions);

        for(int i = 0; i < count; i++){
            int[] pos = validPositions.get(i);
            map[pos[0]][pos[1]] = 15;
        }
    }


    public int getCell(int row, int col){
        return map[row][col];
    }

    public void setCell(int row, int col, int value){
        map[row][col] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
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



    public void clearDotAt(int row, int col){
        map[row][col] = -1;
    }


    public int[][] getMap() {
        return map;
    }
}
