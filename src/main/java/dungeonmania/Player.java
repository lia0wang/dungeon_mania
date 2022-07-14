package dungeonmania;

import java.util.ArrayList;
import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends MovingEntity implements MovementBehaviour{
   
    private List<CollectableItem> inventory;
    private PlayerState playerstate;
    private final String type = "player";

    public Player(int x, int y) {
        super(x, y);
        this.inventory = new ArrayList<CollectableItem>();
        this.playerstate = new DefaultState();
    }

    public void move(Direction direction) {
        // TODO
    }

    public String getType() {
        return this.type;
    }
    
    public List<CollectableItem> getInventory() {
        return this.inventory;
    }

    public PlayerState getPlayerstate() {
        return this.playerstate;
    }

    public void setPlayerState(PlayerState playerstate) {
        this.playerstate = playerstate;
    }
}
