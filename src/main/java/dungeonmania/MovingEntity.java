package dungeonmania;
import dungeonmania.util.Position;

public abstract class MovingEntity {
    private Position position;

    public MovingEntity(int x, int y) {
        Position position = new Position(x, y);
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

}
