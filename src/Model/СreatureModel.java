package Model;

public interface СreatureModel {

    void setDirection(Direction direction);
    void move();
    boolean canMove(int x, int y);
    void resetPosition();

    int getX();
    int getY();
    int getPixelX();
    int getPixelY();
}
