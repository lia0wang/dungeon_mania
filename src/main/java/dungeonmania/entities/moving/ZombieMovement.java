package dungeonmania.entities.moving;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieMovement implements MovementBehaviour {

    @Override
    public void move(Direction direction, MovingEntity entity) {
        Position oldPosition = entity.getPosition();

        // Shuffle the directions in random order
        Direction[] directionOptions = Direction.values();
        List<Direction> directionList = Arrays.asList(directionOptions);
        Collections.shuffle(directionList);

        // Try all directions one by one until a valid move can be made
        while (!directionList.isEmpty()) {
            // Calculate the new position of the zombie based on the random direction
            Direction randDirection = directionList.get(0);
            Position directionPos = randDirection.getOffset();
            int newX = directionPos.getX() + entity.getPosition().getX();
            int newY = directionPos.getY() + entity.getPosition().getY();
            entity.setPosition(new Position(newX, newY));

            if (!entity.getDungeon().checkMove(entity)) {
                entity.setPosition(oldPosition);
                directionList.remove(0);
            } else {
                break;
            }
        }
    }
}
