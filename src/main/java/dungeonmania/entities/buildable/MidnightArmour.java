package dungeonmania.entities.buildable;

import dungeonmania.entities.collectable.CollectableEntity;

public class MidnightArmour extends CollectableEntity{
    private int extraAttackDamage;
    private int defense;

    public MidnightArmour(int x, int y, String type, int attack, int defense) {
        super(x, y, type);
        this.extraAttackDamage = attack;
        this.defense = defense;
    }

    public int getDefenseValue() {
        return this.defense;
    }

    public int getAttackDamage() {
        return this.extraAttackDamage;
    }

}

