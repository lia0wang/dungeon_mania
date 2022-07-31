package dungeonmania.entities.buildable;

import dungeonmania.entities.collectable.CollectableEntity;

public class Sceptre extends CollectableEntity{
    private int effectLasting;

    public Sceptre(int x, int y, String type, int effectLasting) {
        super(x, y, type);
        this.effectLasting = effectLasting;
    }

    public int getEffectLastingPeriod() {
        return this.effectLasting;
    }
}
