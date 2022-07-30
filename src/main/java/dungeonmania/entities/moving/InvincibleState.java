package dungeonmania.entities.moving;

public class InvincibleState implements PlayerState{
    
    private int remainingEffect;
    private boolean queued = false;

    public InvincibleState(int remainingEffect) {
        this.remainingEffect = remainingEffect;
    }

    public int getRemainingEffect() {
        return this.remainingEffect;
    }
    
    public void nextState(Player player) {
        if (this.remainingEffect != 0) {
            setRemainingEffect(this.remainingEffect - 1);
        } else {
            player.setPlayerState(new DefaultState());
        }
    }

    public void setRemainingEffect(int time) {
        this.remainingEffect = time;
    }
    
    public boolean setInvincibleQueued() {
        this.queued = true;
        return this.queued;
    }

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
