package dungeonmania.entities.moving;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends MovingEntity implements MovementBehaviour{
   
    public Player(Position position, int x, int y) {
        super(position, x, y);
    }

    public void move(Direction direction) {
        // TODO
    }
}
