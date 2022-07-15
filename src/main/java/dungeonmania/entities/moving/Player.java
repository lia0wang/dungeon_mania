package dungeonmania.entities.moving;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends MovingEntity implements MovementBehaviour{
    private Inventory inventory;
    private PlayerState playerstate;
    private final String type = "player";
    
    public Player(int x, int y) {
        super(x, y);
        this.inventory = new Inventory();
        this.playerstate = new DefaultState();
    }

    public void move(Direction direction) {
        // TODO
    }

    public String getType() {
        return this.type;
    }
    
    public Inventory getInventory() {
        return inventory;
    }

    public PlayerState getPlayerstate() {
        return this.playerstate;
    }

    public void setPlayerState(PlayerState playerstate) {
        this.playerstate = playerstate;
    }
}
