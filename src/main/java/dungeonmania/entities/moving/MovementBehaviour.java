package dungeonmania.entities.moving;
import dungeonmania.util.Direction;

public interface MovementBehaviour {
    public void move(Direction direction, MovingEntity entity);
}
