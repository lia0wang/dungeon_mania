package dungeonmania.entities.moving;
import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SpiderMovement implements MovementBehaviour {

    @Override
    public void move(Direction direction, MovingEntity entity) {
        /**    
         * Indexes of the adjacent positions around the spawnpoint p
         * 0 1 2
         * 7 p 3
         * 6 5 4
        */
        
        if (entity instanceof Spider) {
            // List of adjacent positions to the spawnpoint
            List<Position> adjacentPos = ((Spider) entity).getSpawnPoint().getAdjacentPositions();

            // Check if spider is still at spawnpoint then move to first position
            if (entity.getPosition().equals(((Spider) entity).getSpawnPoint())) {
                if (!entity.getDungeon().boulderInPosition(adjacentPos.get(1))) {
                    entity.setPosition(adjacentPos.get(1));
                    return;
                } 
                return;
            }

            // Get current position index
            Integer currPosIndex = getCurrentPositionIndex(adjacentPos, entity.getPosition());

            // Get new position index
            Integer newPosIndex = getNewPositionIndex(currPosIndex, entity);

            // If boulder is in path then reverse the direction
            if (!entity.getDungeon().boulderInPosition(adjacentPos.get(newPosIndex))) {
                entity.setPosition(adjacentPos.get(newPosIndex));
            } else {
                Boolean currentDirection = ((Spider) entity).isAnticlockwise();

                // Check if spider is in between two boulders
                if (checkBetweenTwoBoulders(currPosIndex, adjacentPos, entity)) {
                    return;
                } 
                // Reverse direction and get new position
                else {
                    ((Spider) entity).setAnticlockwise(!currentDirection);
                    newPosIndex = getNewPositionIndex(currPosIndex, entity);
                    entity.setPosition(adjacentPos.get(newPosIndex));
                }
            }
        }
    }

    /**
     * Checks if the spider is stuck in between two boulders 
     * @return
     */
    private boolean checkBetweenTwoBoulders(Integer currPosIndex, List<Position> posList, MovingEntity entity) {
        Integer indexFront = (currPosIndex + 9) % 8;
        Integer indexBehind = (currPosIndex + 7) % 8;
    
        Boolean boulderInFront = entity.getDungeon().boulderInPosition(posList.get(indexFront));
        Boolean boulderBehind = entity.getDungeon().boulderInPosition(posList.get(indexBehind));
        return boulderInFront && boulderBehind;
    }

    /**
     * Gets the current position index relative to the spawnpoint
     * @return
     */
    private Integer getCurrentPositionIndex(List<Position> posList, Position currentPos) {
        for (int i = 0; i < posList.size(); i++) {
            if (posList.get(i).equals(currentPos)) {
                return i;
            }
        }

        return null;
    }

    /**
     * Gets the position index relative to the spawnpoint and spider direction
     * @return
     */
    private Integer getNewPositionIndex(Integer currentPosIndex, MovingEntity entity) {
        if (((Spider) entity).isAnticlockwise()) {
            return (currentPosIndex + 7) % 8;
        } else {
            return (currentPosIndex + 9) % 8;
        }
    }
}
