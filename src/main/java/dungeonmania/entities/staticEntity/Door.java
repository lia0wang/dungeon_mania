package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Door extends Entity{
    private int key;

    public Door(int x, int y, String type, Integer key) {
        super(x, y, type);
        this.setCollision(true);
        this.key = key;
    }

    public void interact() {}

}