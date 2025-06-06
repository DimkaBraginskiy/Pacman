package Model;

import java.util.*;

public class MapModel {
    private int rows;
    private int cols;
    private int[][] map;

    int minRow = 1;
    int maxRow = rows - 2;
    int minCol = 1;
    int maxCol = cols - 2;

    private int pacManX = -1;
    private int pacManY = -1;


    public MapModel(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        generateFromPredefinedMazeAndCrop(rows, cols);
        placeBigDots(4);
        makeAllDotsReachableFromSpawn();
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

        // Step 2: Crop smaller center (leave room for walls and dot ring)
        int croppedRows = targetRows - 4;
        int croppedCols = targetCols - 4;
        int startRow = (fullMaze.length - croppedRows) / 2;
        int startCol = (fullMaze[0].length - croppedCols) / 2;

        int[][] cropped = new int[croppedRows][croppedCols];
        for (int i = 0; i < croppedRows; i++) {
            for (int j = 0; j < croppedCols; j++) {
                cropped[i][j] = fullMaze[startRow + i][startCol + j];
            }
        }

        // Step 3: Create final framed map
        int[][] framedMap = new int[targetRows][targetCols];

        // Step 3.1: Fill all with walls
        for (int i = 0; i < targetRows; i++) {
            for (int j = 0; j < targetCols; j++) {
                framedMap[i][j] = 1; // wall
            }
        }

        // Step 3.2: Fill inner border with dots
        for (int i = 1; i < targetRows - 1; i++) {
            for (int j = 1; j < targetCols - 1; j++) {
                if (i == 1 || i == targetRows - 2 || j == 1 || j == targetCols - 2) {
                    framedMap[i][j] = 2; // dot
                }
            }
        }

        // Step 3.3: Place cropped map into center (+2 offset)
        for (int i = 0; i < croppedRows; i++) {
            for (int j = 0; j < croppedCols; j++) {
                framedMap[i + 2][j + 2] = cropped[i][j];
            }
        }

        this.map = framedMap;
        this.rows = targetRows;
        this.cols = targetCols;

        placeGhostBox();
    }

    private void placeGhostBox(){
        int innerCols = cols - 2;

        int r = rows/2;
        int c = rows/2;

        //INNER GHOST SPAWN WALLS:
        if((innerCols % 2) != 0){


            // Walls
            map[r + 1][c] = 1;
            map[r + 1][c + 1] = 1;
            map[r + 1][c - 1] = 1;

            map[r][c + 2] = 1;
            map[r][c - 2] = 1;

            map[r - 1][c - 1] = 1;
            map[r - 1][c + 1] = 1;

            map[r - 1][c - 2] = 1;
            map[r - 1][c + 2] = 1;
            map[r + 1][c - 2] = 1;
            map[r + 1][c + 2] = 1;

            // Dots around the box (ring)
            map[r - 2][c - 2] = 2;
            map[r - 2][c - 1] = 2;
            map[r - 2][c]     = 2;
            map[r - 2][c + 1] = 2;
            map[r - 2][c + 2] = 2;

            map[r - 1][c - 3] = 2;
            map[r - 1][c + 3] = 2;

            map[r][c - 3] = 2;
            map[r][c + 3] = 2;

            map[r + 1][c - 3] = 2;
            map[r + 1][c + 3] = 2;

            map[r + 2][c - 2] = 2;
            map[r + 2][c - 1] = 2;
            map[r + 2][c]     = 2;
            map[r + 2][c + 1] = 2;
            map[r + 2][c + 2] = 2;

            // Spawn area
            map[r][c] = -1;
            map[r][c - 1] = -1;
            map[r - 1][c] = -1;
            map[r][c + 1] = -1;


        }else if((innerCols%2)==0){


            // Walls
            map[r + 1][c] = 1;
            map[r + 1][c + 1] = 1;
            map[r + 1][c - 1] = 1;
            map[r + 1][c - 2] = 1;

            map[r][c + 2] = 1;
            map[r][c - 3] = 1;

            map[r - 1][c - 2] = 1;
            map[r - 1][c + 1] = 1;

            map[r - 1][c - 3] = 1;
            map[r - 1][c + 2] = 1;
            map[r + 1][c - 3] = 1;
            map[r + 1][c + 2] = 1;

            // Dots around the box (ring)
            map[r - 2][c - 3] = 2;
            map[r - 2][c - 2] = 2;
            map[r - 2][c - 1] = 2;
            map[r - 2][c]     = 2;
            map[r - 2][c + 1] = 2;
            map[r - 2][c + 2] = 2;

            map[r - 1][c - 4] = 2;
            map[r - 1][c + 3] = 2;

            map[r][c - 4] = 2;
            map[r][c + 3] = 2;

            map[r + 1][c - 4] = 2;
            map[r + 1][c + 3] = 2;

            map[r + 2][c - 3] = 2;
            map[r + 2][c - 2] = 2;
            map[r + 2][c - 1] = 2;
            map[r + 2][c]     = 2;
            map[r + 2][c + 1] = 2;
            map[r + 2][c + 2] = 2;

            // Spawn area
            map[r][c] = -1;
            map[r][c - 1] = -1;
            map[r - 1][c - 1] = -1;
            map[r][c - 2] = -1;
            map[r][c - 2] = -1;
            map[r][c + 1] = -1;
        }
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

    public void setPacManPosition(int x, int y){
           this.pacManX = x;
           this.pacManY = y;
    }

    public boolean isPacManAt(int row, int col){
        return row == pacManY && col == pacManX;
    }


    public void placeBigDots(int count){
        List<int[]> validPositions = new ArrayList<>();

        for(int row = 1; row < rows -1; row++){
            for(int col = 1; col < cols -1; col++){
                if(map[row][col] == 2){
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
            map[pos[0]][pos[1]] = 3;
        }
    }

    // Ensuring that all the dots are reachable and in case of the dots which are surrounded by walls we eliminate this possibility of happening.
    // With this method a map becomes actually playable. It used to happen with odd map size so now it does not:
    public void makeAllDotsReachableFromSpawn() {
        boolean[][] visited = new boolean[rows][cols];
        Queue<int[]> queue = new LinkedList<>();

        // Use Pac-Man's position if set, otherwise fallback to (1,1)
        int pacManStartY = (pacManY >= 0) ? pacManY : 1;
        int pacManStartX = (pacManX >= 0) ? pacManX : 1;

        visited[pacManStartY][pacManStartX] = true;
        queue.add(new int[]{pacManStartY, pacManStartX});

        // up, down, left, right.......
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // block of code which manages all possible walkable tiles.
        // using bfs algorithm to (Breadth First Search) to find all reachable tiles.
        // we can find walls whcih are unreachable and eliminate it then.......
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int[] dir : directions) {
                int newY = y + dir[0];
                int newX = x + dir[1];

                if (inBounds(newY, newX) && !visited[newY][newX] && map[newY][newX] != 1) {
                    visited[newY][newX] = true;
                    queue.add(new int[]{newY, newX});
                }
            }
        }

        // Check each dot — if it's unreachable, break a wall next to it based from previous observations from previous code block.
        for (int y = 1; y < rows - 1; y++) {
            for (int x = 1; x < cols - 1; x++) {
                if ((map[y][x] == 2 || map[y][x] == 3) && !visited[y][x]) {
                    for (int[] dir : directions) {
                        int ny = y + dir[0];
                        int nx = x + dir[1];

                        if (inBounds(ny, nx) && map[ny][nx] == 1) {
                            map[ny][nx] = -1; // break wall to create access
                            break;
                        }
                    }
                }
            }
        }
    }


    private boolean inBounds(int y, int x) {
        return y >= 0 && y < rows && x >= 0 && x < cols;
    }



    public boolean areAllDotsEaten() {
        for (int[] row : map) {
            for (int cell : row) {
                if (cell == 2 || cell == 3) return false;
            }
        }
        return true;
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


    public void clearDotAt(int row, int col){
        map[row][col] = -1;
    }


    public int[][] getMap() {
        return map;
    }
}
