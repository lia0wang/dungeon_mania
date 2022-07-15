package dungeonmania.entities;

import dungeonmania.entities.moving.Player;

public class EntityFactory {
    public Entity getEntity(String entityType, int x, int y) {
        if (entityType == null) {
            return null;
        }
        if (entityType.equalsIgnoreCase("player")) {
            return new Player(x, y);
        }
        return null;
    }
}
