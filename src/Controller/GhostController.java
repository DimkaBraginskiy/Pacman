package Controller;

import Model.Direction;
import Model.GhostModel;
import Model.MapModel;
import Model.PacManModel;
import View.GhostView;

import javax.swing.*;
import java.util.*;

public class GhostController {
    private final GhostModel model;
    private final GhostView view;
    private Thread movementThread;
    private final Random random = new Random();
    private Direction previousDirection = Direction.NONE;

    private final PacManModel pacManModel;

    public GhostController(GhostModel model, GhostView view, PacManModel pacManModel){
        this.model = model;
        this.view = view;
        this.pacManModel = pacManModel;
        startMovementThread();
    }


    public void startMovementThread(){
        movementThread = new Thread(() -> {
            while(true){
                //Direction nextDir = chooseRandomDirection();
                Direction nextDir = chooseDirection();

                System.out.println("Ghost direction: " + nextDir);

                model.setDirection(nextDir);
                model.move();

                SwingUtilities.invokeLater(() -> view.updatePosition(model.getPixelX(), model.getPixelY()));
                try{
                    Thread.sleep(1000);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });
        movementThread.start();
    }

    private Direction chooseRandomDirection() {
        List<Direction> shuffled = new ArrayList<>(List.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT));
        Collections.shuffle(shuffled); // random order
        for (Direction dir : shuffled) {
            int newX = model.getX(), newY = model.getY();
            switch (dir) {
                case UP -> newY--;
                case DOWN -> newY++;
                case LEFT -> newX--;
                case RIGHT -> newX++;
            }
            if (model.canMove(newX, newY)) {
                return dir;
            }
        }
        return Direction.NONE; // all directions blocked
    }

//    private Direction chooseDirection() {
//        int ghostX = model.getX(), ghostY = model.getY();
//        int pacManX = pacManModel.getX(), pacManY = pacManModel.getY();
//
//        List<Direction> prioritizedDirections = new ArrayList<>();
//
//        if(pacManX < ghostX){
//            prioritizedDirections.add(Direction.LEFT);
//        }
//        if(ghostX < pacManX){
//            prioritizedDirections.add(Direction.RIGHT);
//        }
//        if(pacManY < ghostY){
//            prioritizedDirections.add(Direction.UP);
//        }
//        if(ghostY < pacManY){
//            prioritizedDirections.add(Direction.DOWN);
//        }
//
//
//        // trying to move forward to pacman first if no obstacles occur:
//        for(Direction direction : prioritizedDirections){
//            if(direction == opposite(previousDirection)){
//                continue;
//            }
//
//            int newX = ghostX;
//            int newY = ghostY;
//
//            switch (direction){
//                case LEFT -> newX--;
//                case RIGHT -> newX++;
//                case DOWN -> newY++;
//                case UP -> newY--;
//            }
//
//            if(model.canMove(newX, newY)){
//                previousDirection = direction;
//                return direction;
//            }
//        }
//
//        // if can not move from previous loop:
//        for(Direction direction : Direction.values()){
//            if(direction == Direction.NONE || direction == opposite(previousDirection)){
//                continue;
//            }
//
//
//            int newX = ghostX;
//            int newY = ghostY;
//
//            switch (direction){
//                case UP -> newY--;
//                case DOWN -> newY++;
//                case LEFT -> newX--;
//                case RIGHT -> newX++;
//                default -> {
//                    continue;
//                }
//            }
//
//            if(model.canMove(newX,newY)){
//                previousDirection = direction;
//                return direction;
//            }
//        }
//
//        Direction reverse = opposite(previousDirection);
//        int reverseX = ghostX, reverseY = ghostY;
//        switch (reverse){
//            case LEFT -> reverseX--;
//            case RIGHT -> reverseX++;
//            case DOWN -> reverseY++;
//            case UP -> reverseY--;
//        }
//
//        if(model.canMove(reverseX, reverseY)){
//            previousDirection = reverse;
//            return reverse;
//        }
//
//        return Direction.NONE; // all directions blocked
//    }

    private Direction chooseDirection() {
        int[][] map = model.getMap(); // full map view
        int rows = map.length;
        int cols = map[0].length;

        int startX = model.getX();
        int startY = model.getY();
        int targetX = pacManModel.getX();
        int targetY = pacManModel.getY();

        boolean[][] visited = new boolean[rows][cols];
        Direction[][] cameFrom = new Direction[rows][cols];

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startY, startX});
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0], x = current[1];

            if (x == targetX && y == targetY) break;

            for (Direction dir : Direction.values()) {
                if (dir == Direction.NONE) continue;

                int newX = x, newY = y;
                switch (dir) {
                    case LEFT -> newX--;
                    case RIGHT -> newX++;
                    case UP -> newY--;
                    case DOWN -> newY++;
                }

                if (newX < 0 || newX >= cols || newY < 0 || newY >= rows) continue;
                if (!model.canMove(newX, newY)) continue;
                if (visited[newY][newX]) continue;

                visited[newY][newX] = true;
                cameFrom[newY][newX] = dir;
                queue.add(new int[]{newY, newX});
            }
        }

        // Backtrack to determine first move
        int backY = targetY;
        int backX = targetX;

        Direction lastDirection = null;

        while (!(backY == startY && backX == startX)) {
            Direction from = cameFrom[backY][backX];
            if (from == null) return Direction.NONE;

            int prevX = backX, prevY = backY;
            switch (from) {
                case LEFT -> prevX++;
                case RIGHT -> prevX--;
                case UP -> prevY++;
                case DOWN -> prevY--;
            }

            if (prevX == startX && prevY == startY) {
                lastDirection = from;
                break;
            }

            backX = prevX;
            backY = prevY;
        }

        return lastDirection != null ? lastDirection : Direction.NONE;
    }



    //opposite direction function
    private Direction opposite(Direction dir) {
        return switch (dir) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
            default -> Direction.NONE;
        };
    }


}
