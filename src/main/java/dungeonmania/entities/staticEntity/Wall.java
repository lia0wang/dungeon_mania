package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Wall extends Entity{

    public Wall(int x, int y, String type) {
        super(x, y, type);
        this.setCollision(true);
    }
}