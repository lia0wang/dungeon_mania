package dungeonmania.entities.moving;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class ZombieMovement implements MovementBehaviour {

    @Override
    public void move(Direction direction, MovingEntity entity) {
        Position oldPosition = entity.getPosition();

        // Shuffle the directions in random order
        Direction[] directionOptions = Direction.values();
        List<Direction> directionList = Arrays.asList(directionOptions);
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        // Try all directions one by one until a valid move can be made
        for (int i = 0; i < indexList.size(); i++) {
            // Calculate the new position of the zombie based on the random direction
            Direction randDirection = directionList.get(indexList.get(i));
            Position directionPos = randDirection.getOffset();
            int newX = directionPos.getX() + entity.getPosition().getX();
            int newY = directionPos.getY() + entity.getPosition().getY();
            entity.setPosition(new Position(newX, newY));

            if (!entity.getDungeon().checkMove(entity)) {
                entity.setPosition(oldPosition);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] indexOrder = {0, 1, 2, 3};
        List<Integer> indexList = Arrays.asList(indexOrder);
        Collections.shuffle(indexList, new Random(1));

        for (int i = 0; i < 4; i++) {
            System.out.println(indexList.get(i));
        }
    }
}
