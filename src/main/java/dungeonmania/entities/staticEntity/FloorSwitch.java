package dungeonmania.entities.staticEntity;

import dungeonmania.entities.Entity;

public class FloorSwitch extends Entity{
    private boolean triggered;

    public FloorSwitch(int x, int y, String type) {
        super(x, y, type);
        this.triggered = false;
    }

    public boolean isTriggered() {
        return triggered;
    }

    public void setTriggered(Boolean trig) {
        this.triggered = trig;
    }

}