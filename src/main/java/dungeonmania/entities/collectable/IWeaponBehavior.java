package dungeonmania.entities.collectable;

/**
 * interface for equipment behavior - Sword, Shield, Bow.
 * 
 * @author Wang Liao, <add_your_name>
 * @version 1.0
 *
 */
public interface IWeaponBehavior {
    /**
     * Set the attack damage of the weapon.
     *
     * @param attackDamage
     */
    public void setAttackDamage(int attackDamage);

    /**
     * Get the attack damage of the weapon.
     *
     * @return attackDamage
     */
    public float getAttackDamage();
}
