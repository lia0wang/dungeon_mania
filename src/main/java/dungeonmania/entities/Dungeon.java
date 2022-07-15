package dungeonmania.entities;

import java.util.List;

public class Dungeon {
    private List<Entity> entities;

    public Dungeon(List<Entity> entities) {
        this.entities = entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    /**
     * Checks if a move can be made
     */
    public boolean checkMove(Entity entity) {
        if (this.entities.isEmpty()) {
            return true;
        }

        for (Entity e : entities) {
            if (e.isAtSamePosition(entity)) {
                return false;
            }
        }
        return true;
    }
}
