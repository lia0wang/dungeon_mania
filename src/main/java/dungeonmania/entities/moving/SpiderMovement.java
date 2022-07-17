package dungeonmania.entities.moving;
import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class SpiderMovement implements MovementBehaviour {

    @Override
    public void move(Direction direction, MovingEntity entity) {
        if (entity instanceof Spider) {
            int currentX = entity.getPosition().getX();
            int currentY = entity.getPosition().getY();
            Position newPos = null;

            switch (((Spider) entity).getCurrentTick()) {
                case 0: 
                case 6:
                case 7:
                    newPos = new Position(currentX, currentY - 1);
                    break;
                case 1:
                case 8:
                    newPos = new Position(currentX + 1, currentY);
                case 2:
                case 3:
                    newPos = new Position(currentX, currentY + 1);
                case 4:
                case 5:
                    newPos = new Position(currentX - 1, currentY);
            }
            entity.setPosition(newPos);
        }
    }
}
