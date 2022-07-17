package dungeonmania.entities.moving;

import dungeonmania.util.Position;
import dungeonmania.util.Direction;

public class PlayerMovement implements MovementBehaviour {
    @Override
    public void move(Direction direction, MovingEntity entity) {
        Position directionPos = direction.getOffset();
        int newX = directionPos.getX() + entity.getPosition().getX();
        int newY = directionPos.getY() + entity.getPosition().getY();
        Position targetPosition = new Position(newX, newY);

        if (entity.getDungeon().checkMove(entity)) {
            entity.setPosition(targetPosition);
        }   
    }
}
