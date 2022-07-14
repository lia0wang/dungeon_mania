package dungeonmania;

public class InvincibleState implements PlayerState{
    private String playerstate;

    public InvincibleState() {
        this.playerstate = "InvincibleState";
    }

    @Override
    public void becomeInvincible(Player player) {
        player.setPlayerState(new InvincibleState());
    }

    @Override
    public void becomeInvisible(Player player) {
        player.setPlayerState(new InvisibleState());
    }

    @Override
    public void gameOver(Player player) {
        player.setPlayerState(new GameOverState());
        System.out.println("Game Over!");
    }
    
}
