package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;

public class ZombieToast extends MovingEntity {
    private MovementBehaviour movement;
    
    public ZombieToast(int x, int y, String type, Dungeon dungeon) {
        super(x, y, type, dungeon);
        this.movement = new ZombieMovement();
    }

    public MovementBehaviour getMovement() {
        return movement;
    }
    
    public void move(Direction direction) {
        movement.move(direction, this);
    }
}
