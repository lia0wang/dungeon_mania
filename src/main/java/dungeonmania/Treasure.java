package dungeonmania;

import dungeonmania.util.Position;

public class Treasure extends CollectableItem{
    private final String type = "treasure";
    private Position position;
    private String id;

    public Treasure(Position posiiton, int x, int y, String id) {
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
        // TODO: Treasure can be used to build shield & bribe mercenary &fight with enemies
        
    }

    @Override
    public String getId() {
        return this.id;
    }

    
}
