package dungeonmania;

public class GameOverState implements PlayerState {
    private String playerstate;

    public GameOverState() {
        this.playerstate = "GameOverState";
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
}
