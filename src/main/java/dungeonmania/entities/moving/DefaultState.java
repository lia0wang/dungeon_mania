package dungeonmania.entities.moving;

public class DefaultState implements PlayerState{

    @Override
    public void becomeInvincible(Player player, int effect) {
        player.setPlayerState(new InvincibleState(effect));
    }

    @Override
    public void becomeInvisible(Player player, int effect) {
        player.setPlayerState(new InvisibleState(effect));
    }

    @Override
    public void gameOver(Player player) {
        player.setPlayerState(new GameOverState());
        System.out.println("Game Over!");
    }

    @Override
    public void backToDefault(Player player) {
        player.setPlayerState(new DefaultState());
    }
}
