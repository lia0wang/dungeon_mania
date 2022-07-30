package dungeonmania.entities.moving;

public class GameOverState implements PlayerState {

    @Override
    public void becomeInvincible(Player player, int effect) {
        System.out.println("Game Over!");
    }

    @Override
    public void becomeInvisible(Player player, int effect) {
        System.out.println("Game Over!");
    }

    @Override
    public void gameOver(Player player) {
        player.setPlayerState(new GameOverState());
        System.out.println("Game Over!");
    }

    @Override
    public void backToDefault(Player player) {
        System.out.println("Game Over!");
    }
}
