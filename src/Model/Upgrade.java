package Model;

public class Upgrade {
    private int row;
    private int col;
    private UpgradeType upgradeType;
    private long spawnTime;


    public Upgrade(int row, int col, UpgradeType upgradeType) {
        this.row = row;
        this.col = col;
        this.upgradeType = upgradeType;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public UpgradeType getUpgradeType() {
        return upgradeType;
    }
}
