package dungeonmania.entities.collectable;

import dungeonmania.util.Position;

public class Key extends CollectableItem {
    private final String type = "key";
    private Position position;
    private String id;

    public Key(Position posiiton, int x, int y, String id) {
        super(posiiton, y, y);
        this.id = id;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public void useItem() {
        // TODO: 
        // note: key is used to open the door by moving through it & to build shiled
        // key will disappear once used
        // If key is used before opening its door, its door will be locked forever.
        
    }

    @Override
    public String getId() {
        return this.id;
    }
    
}
