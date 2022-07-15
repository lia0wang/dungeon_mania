package dungeonmania.entities.moving;

public class GameOverState implements PlayerState {
    private String playerState;

    public GameOverState() {
        this.playerState = "GameOverState";
    }

    @Override
    public void becomeInvincible(Player player) {
        System.out.println("Game Over!");
    }

    @Override
    public void becomeInvisible(Player player) {
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
