package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Portal extends Entity{
    private boolean hasCollision;
    private String colour;

    public Portal(int x, int y, String type, String colour) {
        super(x, y, type);
        this.hasCollision = false;
        this.colour = colour;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public String getColour() {
        return colour;
    }

    public void interact() {}

}