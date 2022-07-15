package dungeonmania.entities.moving;

import dungeonmania.util.Direction;

public class Player extends MovingEntity implements MovementBehaviour{
    private Inventory inventory;
    private PlayerState playerState;
    
    public Player(int x, int y) {
        super(x, y, "player");
        this.inventory = new Inventory();
        this.playerState = new DefaultState();
    }

    public void move(Direction direction) {
        // TODO
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
