package dungeonmania.entities.moving;

public interface PlayerState {
    public void becomeInvincible(Player player);
    public void becomeInvisible(Player player);
    public void gameOver(Player player);
    public void backToDefault(Player player);
}