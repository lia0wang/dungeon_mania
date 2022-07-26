package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Door extends Entity{
    private int keyId;
    private boolean doorState; 

    public Door(int x, int y, String type, Integer keyId) {
        super(x, y, type);
        this.setCollision(true);
        this.keyId = keyId;
        this.doorState = false;
    }

    public int getKeyId() {
        return this.keyId;
    }

    public boolean isOpen() {
        return this.doorState;
    }

    public void setDoorOpen() {
        this.doorState = true;
    }

    public void interact() {}

}