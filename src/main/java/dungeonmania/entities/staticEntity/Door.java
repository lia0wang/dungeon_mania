package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Door extends Entity{
    private boolean hasCollision;
    private String key;

    public Door(int x, int y, String type, String key) {
        super(x, y, type);
        this.hasCollision = true;
        this.key = key;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}