package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;

public class Mercenary extends MovingEntity {
    MovementBehaviour movement;

    public Mercenary(int x, int y, String type, Dungeon dungeon) {
        super(x, y, type, dungeon);
        this.movement = new MercenaryMovement();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void move(Direction direction) {
        // TODO Auto-generated method stub
        
    }
    
}