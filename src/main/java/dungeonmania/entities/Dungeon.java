package dungeonmania.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.collectable.Treasure;
import dungeonmania.entities.goal.ComplexGoalLogic;
import dungeonmania.entities.moving.MovingEntity;
import dungeonmania.entities.moving.Player;
import dungeonmania.entities.moving.Spider;
import dungeonmania.entities.staticEntity.*;
import dungeonmania.entities.battles.*;;

public class Dungeon {
    private JSONObject configs;
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> enemies = new ArrayList<>();
    private ArrayList<Battle> battles = new ArrayList<>();
    private String goals = "";
    private ComplexGoalLogic goal;
    private String Id;
    private String name;
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
        
        // store and set goals fron JSONObject
        //StoreDungeonGoal allgoal = new StoreDungeonGoal(this.dunegon, goal);
        //allgoal.addGoals(new JSONObject(dungeonMap).getJSONObject("goal-condition"), goal);
        //setGoal(goal);

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
     * Get an array of all battles that have occurred on the map.
     *
     * @return battles
     */
    public ArrayList<Battle> getBattles() {
        return battles;
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
     * Add a battle to the dungeon.
     *
     * @param newBattle
     */
    public void addBattle(Battle newBattle) {
        battles.add(newBattle);
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
     * Set the name of the dungeon map.
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the dungeon map.
     *
     * @return name
     */
    public String getName() {
        return name;
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
                    Player newPlayer = new Player(x, y, type, this);
                    this.player = newPlayer;
                    entities.add(newPlayer);
                case "wall":
                    entities.add(new Wall(x, y, type));
                    continue;
                case "exit":
                    entities.add(new Exit(x, y, type));
                    continue;
                case "boulder":
                    entities.add(new Boulder(x, y, type));
                    continue;
                case "switch":
                    entities.add(new FloorSwitch(x, y, type));
                    continue;
                case "door":
                    Integer key = currEntity.getInt("key");
                    entities.add(new Door(x, y, type, key));
                    continue;
                case "portal":
                    String colour = currEntity.getString("colour");
                    entities.add(new Portal(x, y, type, colour));
                    continue;
                case "zombie_toast_spawner":
                    entities.add(new ZombieToastSpawner(x, y, type));
                    continue;
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
        goals = doGoaltoString(goals, allGoals);
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
            
            if (goal1.has("subgoals")) {
                currGoals += "(";
                currGoals = doGoaltoString(currGoals, goal1);
                currGoals += ")";
            } else {
                currGoals += ":" + goal1.getString("goal");
            }
            currGoals += " " + goalSetting + " ";

            if (goal2.has("subgoals")) {
                currGoals += "(";
                currGoals = doGoaltoString(currGoals, goal2);
                currGoals += ")";
            } else {
                currGoals += ":" + goal2.getString("goal");
            }
        } else {
            String goal1 = remainingGoals.getString("goal");
            
            currGoals += ":" + goal1;
        }

        return currGoals;
    }

    /*
    public ComplexGoalLogic getGoal() {
        return goal;
    }

    public void setGoal(ComplexGoalLogic goal) {
        this.goal = goal;
    }
    */

    public void updateGoal() {
        String curString = getGoals();
        Player player = getPlayer();
        ArrayList<Entity> entitiesAtPlayer = getAllEntitiesinPosition(player.getPositionX(), player.getPositionY());
			
        if (entitiesAtPlayer.stream().anyMatch(entity -> entity instanceof Exit)) {
            curString = curString.replace(":exit", "");
        } else if (getEnemies().size() == 0) {
            curString = curString.replace(":enemies", "");
        } else if (getFloorSwitches().stream().allMatch(s->((FloorSwitch) s).isTriggered())) {
            curString = curString.replace(":boulders", "");
        } else if (getTreasures().size() == 0) {
            curString = curString.replace(":treasure", "");
        }
        goals = curString;
    }

    public boolean goalReached() {
        return goal.goalAchieved();
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
    
    /**
     * Get an entity by its ID.
     */
    public Entity getEntityById(String id) {
        return entities.stream().filter(entity -> entity.getId().equals(id)).findFirst().orElse(null);
    }
    
    /**
     * return all moving entities on the map
     * @return
     */
    public ArrayList<MovingEntity> getAllMovingEntitiesButPlayer() {
        return entities.stream().filter(entity -> entity instanceof MovingEntity && !entity.getType().equals("player")).map(MovingEntity.class::cast)
        .collect(Collectors.toCollection(ArrayList::new));
    }

}