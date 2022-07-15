package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class ZombieToastSpawner extends Entity{
    private boolean hasCollision;

    public ZombieToastSpawner(int x, int y, String type) {
        super(x, y, type);
        this.hasCollision = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}