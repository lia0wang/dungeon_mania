package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public abstract class Exit extends Entity{
    public Exit(int x, int y, String type) {
        super(x, y, type);
    }

    public void interact() {}

}
