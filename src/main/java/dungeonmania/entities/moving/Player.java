package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.util.Direction;

public class Player extends MovingEntity {
    private Inventory inventory;
    private MovementBehaviour movement;
    private PlayerState playerState;
    
    public Player(int x, int y, String type, Dungeon dungeon) {
        super(x, y, "player", dungeon);
        this.inventory = new Inventory();
        this.playerState = new DefaultState();
        this.movement = new PlayerMovement();
    }

    public MovementBehaviour getMovement() {
        return movement;
    }    

    public void move(Direction direction) {
        movement.move(direction, this);
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public PlayerState getPlayerState() {
        return this.playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }
}
