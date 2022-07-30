package dungeonmania.entities.moving;

public interface PlayerState {
    public void becomeInvincible(Player player, int effect);
    public void becomeInvisible(Player player, int effect);
    public void gameOver(Player player);
    public void backToDefault(Player player);
}
