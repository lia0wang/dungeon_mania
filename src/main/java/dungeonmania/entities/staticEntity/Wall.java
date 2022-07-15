package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Wall extends Entity{
    private boolean hasCollision;

    public Wall(int x, int y, String type) {
        super(x, y, type);
        this.hasCollision = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }
}