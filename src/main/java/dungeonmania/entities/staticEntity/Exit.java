package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public abstract class Exit extends Entity{
    private boolean hasCollision;

    public Exit(int x, int y, String type) {
        super(x, y, type);
        this.hasCollision = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public void interact() {}

}
