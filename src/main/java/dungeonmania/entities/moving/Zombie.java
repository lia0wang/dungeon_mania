package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;

public class Zombie extends MovingEntity {
    private MovementBehaviour movement;
    
    public Zombie(int x, int y, String type, Dungeon dungeon) {
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
