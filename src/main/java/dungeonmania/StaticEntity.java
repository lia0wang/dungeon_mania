package dungeonmania;

import dungeonmania.util.Position;

public abstract class StaticEntity {
    private Position position;
    
    public StaticEntity(int x, int y, int hasCollision) {
        Position position = new Position(x, y, hasCollision);
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public abstract String getType();
    public abstract void interact();
    public abstract String getId();

}
