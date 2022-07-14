package dungeonmania.entities.collectable;

import dungeonmania.util.Position;

public abstract class CollectableItem {
    private Position position;
    
    public CollectableItem(Position position, int x, int y) {
        position = new Position(x, y);
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public abstract String getType();
    public abstract void useItem();
    public abstract String getId();

}
