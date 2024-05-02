package dci.j24e1.group1.battleshipvscomputer;

public class Ship {
    private int id;
    private int x;
    private int y;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ship(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
