package dungeonmania.entities.moving;
import dungeonmania.util.Position;

public class MovingEntity {
    private Position position;

    public MovingEntity(Position position, int x, int y) {
        position = new Position(x, y);
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

}
