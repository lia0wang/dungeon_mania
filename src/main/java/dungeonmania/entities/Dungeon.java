package dungeonmania.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.collectable.Treasure;
import dungeonmania.entities.moving.Player;
import dungeonmania.entities.moving.Spider;
import dungeonmania.entities.staticEntity.*;

public class Dungeon {
    private JSONObject configs;
    private Player player;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> enemies;
    private String goals = "";
    private String Id;
    private static Integer nextDungeonId = 0;
    
    /**
     * Constructor for Dungoen
     *
     * @param dungeonMap
     * @param configs
     */
    public Dungeon(String dungeonMap, String configs) {
        this.configs = new JSONObject(configs);
        this.populate(new JSONObject(dungeonMap));
        this.Id = "dungeon_" + Integer.toString(nextDungeonId);
        nextDungeonId++;
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
     * Return the player in this dungeon
     * @return
     */
    public Player getPlayer() {
        return this.player;
    }
    
    /** 
     * Get dungeon Id
     *
     * @return Id
     */
    public String getId() {
        return Id;
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
     * Get all entities that are in (x,y) on the map
     * 
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Entity> getAllEntitiesinPosition(int x, int y) {
        return entities.stream().filter(entity -> (entity.getPositionX() == x && entity.getPositionY() == y))
        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Add an entity to the dungeon.
     *
     * @param newEntity
     */
    public void addEntity(Entity newEntity) {
        entities.add(newEntity);
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
     * Get an array of all treasures that are still on the map
     * 
     * @return
     */
    public ArrayList<Entity> getTreasures() {
        return entities.stream().filter(entity -> entity.getType() == "treasure").map(Treasure.class::cast)
				.collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get an array of all switches that are on the map
     * 
     * @return
     */
    public ArrayList<Entity> getFloorSwitches() {
        return entities.stream().filter(entity -> entity.getType() == "switch").map(FloorSwitch.class::cast)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    
    /**
     * Add an enemy to the dungeon.
     *
     * @param newEntity
     */
    public void addEnemy(Entity newEnemy) {
        enemies.add(newEnemy);
    }

    /**
     * Get a string of all goals.
     *
     * @return goals
     */
    public String getGoals() {
        return goals;
    }

    /**
     * Populates the dungeon class with entities and stores the goals specified by the map.
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
                    entities.add(new Player(x, y, type, this));
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
                    entities.add(new Spider(x, y, type, this));
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

        JSONObject allGoals = configuration.getJSONObject("goal-condition");
        doGoaltoString(goals, allGoals);
    }
    
    /**
     * Converts the map goals into a string, with a recursive method.
     * 
     * @param currGoals, remainingGoals
     * @return string
     */
    public String doGoaltoString(String currGoals, JSONObject remainingGoals) {
        if (remainingGoals.has("subgoals")) {
            String goalSetting = remainingGoals.getString("goal");
            JSONObject goal1 = remainingGoals.getJSONArray("subgoals").getJSONObject(0);
            JSONObject goal2 = remainingGoals.getJSONArray("subgoals").getJSONObject(1);
            
            currGoals += ":" + goal1.getString("goal") + " " + goalSetting + " ";

            if (goal2.has("subgoals")) {
                currGoals += "(";
                doGoaltoString(currGoals, goal2);
                currGoals += ")";
            } else {
                currGoals += goal2;
            }
        } else {
            String goal1 = remainingGoals.getString("goal");
            
            currGoals += ":" + goal1;
        }

        return currGoals;
    }

    /**
     * Checks if a move can be made
     * 
     * @param entity
     * @return boolean
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
