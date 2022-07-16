package dungeonmania.entities;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.staticEntity.*;

public class Dungeon {
    private JSONObject configs;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> enemies;
    private ArrayList<String> goals;
    private String goalSetting = "AND";
    
    /**
     * Constructor for Dungoen
     *
     * @param dungeonMap
     * @param configs
     */
    public Dungeon(String dungeonMap, String configs) {
        this.configs = new JSONObject(configs);
        this.populate(new JSONObject(dungeonMap));
    }

    /**
     * Get a JSONObject of all configurations.
     *
     * @return configs
     */
    public JSONObject getConfigs() {
        return configs;
    }

    /**
     * Get an array of all non-enemy entities that are still on the map.
     *
     * @return entities
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Get an array of all enemies that are still on the map.
     *
     * @return enemies
     */
    public ArrayList<Entity> getEnemies() {
        return enemies;
    }

    /**
     * Get an array of all goals.
     *
     * @return goals
     */
    public ArrayList<String> getGoals() {
        return goals;
    }

    /**
     * Populates the dungeon class with entities specified by the map.
     *
     * @param configuration
     */
    public void populate(JSONObject configuration) {
        JSONArray allEntities = configuration.getJSONArray("entities");
        for (Object e : allEntities) {

            JSONObject currEntity = (JSONObject) e;
            int x = currEntity.getInt("x");
            int y = currEntity.getInt("y");
            String type = currEntity.getString("type");

            switch (type) {
                case "player":
                case "wall":
                    entities.add(new Wall(x, y, type));
                case "exit":
                    entities.add(new Exit(x, y, type));
                case "boulder":
                    entities.add(new Boulder(x, y, type));
                case "switch":
                    entities.add(new FloorSwitch(x, y, type));
                case "door":
                    String key = currEntity.getString("key");
                    entities.add(new Door(x, y, type, key));
                case "portal":
                    String colour = currEntity.getString("colour");
                    entities.add(new Door(x, y, type, colour));
                case "zombie_toast_spawner":
                    entities.add(new ZombieToastSpawner(x, y, type)); 
                case "spider":

                case "zombie_toast":
                case "mercenary":
                case "treasure":
                case "key":
                case "invincibility_potion":
                case "invisibility_potion":
                case "wood":
                case "arrow":
                case "bomb":
                case "sword":
            }
        }

        JSONArray allGoals = configuration.getJSONArray("goal-condition");

    }
    
    /**
     * Checks if a move can be made
     */
    public boolean checkMove(Entity entity) {
        if (this.entities.isEmpty()) {
            return true;
        }

        for (Entity e : entities) {
            if (e.isAtSamePosition(entity) && e.getCollision()) {
                return false;
            }
        }
        return true;
    }
}
