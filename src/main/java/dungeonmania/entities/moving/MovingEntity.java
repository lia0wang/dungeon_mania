package dungeonmania.entities.moving;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;

public abstract class MovingEntity extends Entity{
    protected Dungeon dungeon;
    private float health;
    private float attackDamage;

    public MovingEntity(int x, int y, String type, Dungeon dungeon, float health, float attackDamage) {
        super(x, y, type);
        this.dungeon = dungeon;
        this.health = health;
        this.attackDamage = attackDamage;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }
    
    public float getHealth() {
        return health;
    }
    
    public float getAttackDamage() {
        return attackDamage;
    }
    
    public void setHealth(float health) {
        this.health = health;
    }
    
    public void setAttackDamage(float attackDamage) {
        this.attackDamage = attackDamage;
    }
}
