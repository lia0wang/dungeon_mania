package dungeonmania;

import java.util.List;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

public class Player extends MovingEntity implements MovementBehaviour{
   
    private List<CollectableItem> inventory;
    private PlayerState playerstate;

    public Player(Position position, int x, int y) {
        super(position, x, y);
    }

    public void move(Direction direction) {
        // TODO
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
