package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Boulder extends Entity{
    public Boulder(int x, int y, String type) {
        super(x, y, type);
        this.setCollision(true);
    }

    public Boulder() {
        super(0, 0, "boulder");
        this.setCollision(true);
    }
}
