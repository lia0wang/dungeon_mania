package dungeonmania.entities.goal;

import dungeonmania.entities.Dungeon;

public abstract class LeafGoal implements Goal{
    private Dungeon dungeon;

    public LeafGoal(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Dungeon getDungeon() {
		return dungeon;
	}
    
    public abstract boolean goalAchieved(String curString);
    public abstract String update(String curString);
}
