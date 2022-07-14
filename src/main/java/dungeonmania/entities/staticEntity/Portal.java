package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class Portal extends BaseEntity{
    private boolean hasCollision;
    private String colour;

    public Portal(String id, String type, Position position, String colour) {
        super(id, type, position);
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