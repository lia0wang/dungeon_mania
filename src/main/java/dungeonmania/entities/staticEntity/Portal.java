package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class Portal extends Entity{
    private String colour;

    public Portal(int x, int y, String type, String colour) {
        super(x, y, type);
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}