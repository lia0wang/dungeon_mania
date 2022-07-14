package dungeonmania.entities.staticEntity;

import dungeonmania.entities.BaseEntity;
import dungeonmania.util.Position;

public class FloorSwitch extends BaseEntity{
    private boolean hasCollision;
    private boolean triggered;

    public FloorSwitch(String id, String type, Position position) {
        super(id, type, position);
        this.hasCollision = false;
        this.triggered = false;
    }

    public boolean hasCollision() {
        return hasCollision;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(Boolean  trig) {
        this.triggered = trig;
    }

}