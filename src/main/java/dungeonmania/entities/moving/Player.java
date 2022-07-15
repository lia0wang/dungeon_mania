package dungeonmania.entities.moving;

import dungeonmania.util.Direction;
import dungeonmania.util.Position;

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
        Position directionPos = direction.getOffset();
        int newX = directionPos.getX() + this.getPosition().getX();
        int newY = directionPos.getY() + this.getPosition().getY();
        Position targetPosition = new Position(newX, newY);

        if (this.getDungeon().checkMove(this)) {
            this.setPosition(targetPosition);
        }
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
