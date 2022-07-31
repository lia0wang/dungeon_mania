package dungeonmania.entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.entities.collectable.*;
import dungeonmania.entities.goal.*;
import dungeonmania.entities.moving.*;
import dungeonmania.entities.staticEntity.*;
import dungeonmania.util.*;
import dungeonmania.entities.battles.*;

public class Dungeon {
    private JSONObject configs;
    private Player player;
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> enemies = new ArrayList<>();
    private ArrayList<Battle> battles = new ArrayList<>();
    private String goals = "";
    private ComplexGoalLogic goalStructure;
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
        populate(new JSONObject(dungeonMap), new JSONObject(configs));
        this.Id = "dungeon_" + Integer.toString(nextDungeonId);
        goalStructure = new AndGoal();

        JSONObject goalExpression = new JSONObject(dungeonMap).getJSONObject("goal-condition");
        StoreDungeonGoal g = new StoreDungeonGoal(this);
        g.addGoals(goalExpression, this.goalStructure);
        setGoal(goalStructure);

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
    public ArrayList<Entity> getAllEntitiesInPosition(int x, int y) {
        return entities.stream().filter(entity -> (entity.getPositionX() == x && entity.getPositionY() == y))
        .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Get all enemies that are in (x,y) on the map
     * 
     * @param x
     * @param y
     * @return
     */
    public ArrayList<Entity> getAllEnemiesinPosition(int x, int y) {
        return enemies.stream().filter(entity -> (entity.getPositionX() == x && entity.getPositionY() == y))
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
     * checks if the player has enteres a battle, either from the player moving or enemy moving.
     *
     * @return name
     */
    public boolean doBattles() {
        if (player.getPlayerState() instanceof InvisibleState) {
            return true;
        }

        ArrayList<Entity> enemiesInPos = getAllEnemiesinPosition(player.getPositionX(), player.getPositionY());
        for (Entity e : enemiesInPos) {
            Battle nextBattle = new Battle((MovingEntity)e, this, configs);

            if (!nextBattle.getPlayerStatus()) {
                entities.remove(player);
                return false;
            }
            battles.add(nextBattle);
        }
        return true;
    }
    
    /**
     * Populates the dungeon class with entities and stores the goals specified by the map.
     *
     * @param configuration
     */
    public void populate(JSONObject dungeon, JSONObject config) {
        JSONArray allEntities = dungeon.getJSONArray("entities");
        int invincibleDuration = config.getInt("invincibility_potion_duration");
        int invisibleDuration = config.getInt("invisibility_potion_duration");

        for (Object e : allEntities) {

            JSONObject currEntity = (JSONObject) e;
            int x = currEntity.getInt("x");
            int y = currEntity.getInt("y");
            String type = currEntity.getString("type");

            switch (type) {
                case "player":
                    Player newPlayer = new Player(x, y, this);
                    this.player = newPlayer;
                    entities.add(newPlayer);
                    continue;
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
                    entities.add(new Spider(x, y, type, this, config.getInt("spider_attack"), config.getInt("spider_health")));
                    continue;
                case "zombie_toast":
                    entities.add(new ZombieToast(x, y, type, this, config.getInt("zombie_attack"), config.getInt("zombie_health")));
                    continue;
                case "mercenary":
                case "treasure":
                    entities.add(new Treasure(x, y, type));
                    continue;
                case "key":
                    entities.add(new Key(x, y, currEntity.getInt("key")));
                    continue;
                case "invincibility_potion":
                    entities.add(new InvincibilityPotion(x, y, type, invincibleDuration));
                    continue;
                case "invisibility_potion":
                    entities.add(new InvisibilityPotion(x, y, type, invisibleDuration));
                    continue;
                case "wood":
                case "arrow":
                case "bomb":
                case "sword":
            }
        }

        JSONObject goalExpression = dungeon.getJSONObject("goal-condition");
        
        // create the goalStructure
        
        goals = doGoaltoString(goals, goalExpression);
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

    
    public ComplexGoalLogic getGoal() {
        return goalStructure;
    }

    public void setGoal(ComplexGoalLogic goalStrucComplexGoalLogic) {
        this.goalStructure = goalStrucComplexGoalLogic;
    }
    
    public void setGoalString(String curString) {
        this.goals = curString;
    }

    /**
     * update the goalString after a tick
     */
    public void updateGoal() {
        String curString = getGoals();
        if (goalStructure.goalAchieved(curString)) {
            setGoalString("");
        }

        if (curString.contains(":enemies") && getEnemies().size() == 0) {
            setGoalString(curString.replace(":enemies", ""));
        } else if (curString.contains(":boulders") && getFloorSwitches().stream().allMatch(s->((FloorSwitch) s).isTriggered())) {
            setGoalString(curString.replace(":boulders", ""));
        } else if (curString.contains(":treasure") && getTreasures().size() == 0) {
            setGoalString(curString.replace(":treasure", ""));
        } 
        
        //Player player = getPlayer();
        //ArrayList<Entity> entitiesAtPlayer = getAllEntitiesinPosition(player.getPositionX(), player.getPositionY());
        if (playerReachExit()) {
            setGoalString(curString.replace(":exit", ""));
        } 

    }
    
    // helper function, may be deleted later
    public Boolean playerReachExit() {
        for (Entity e : getEntities()) {
            if (e instanceof Exit) {
                if (e.getPosition() == getPlayer().getPosition()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * pick up entities at a tick
     * @param entity
     */
    public void pickUpItem() {
        ArrayList<Entity> l = getAllEntitiesInPosition(this.player.getPositionX(), this.player.getPositionY());
        for (Entity e : l) {
            if (e instanceof CollectableEntity) {
                ((CollectableEntity) e).collectedByPlayer(this.player, entities);
            }
            if (e instanceof Door){
                useKeyOpenDoor((Door)e);
            }
        }
    }

    public void useKeyOpenDoor(Door door) {
        if (!door.isOpen()) {
            int keyId = door.getKeyId();
            this.player.getKeyInInventory(keyId).consumedByPlayer(this.player);
            door.setDoorOpen();
        }
    }

    public void activateSwitch(Boulder boulder) {
        for (Entity e : entities) {
            if (e instanceof FloorSwitch) {
                if (e.isAtSamePosition(boulder)) {
                    FloorSwitch FloorSwitch = (FloorSwitch) e;
                    FloorSwitch.setTriggered(true);
                }
            }
        }
    }

    public void updatePotionEffect(PlayerState state) {
        if (state instanceof InvincibleState) {
            ((InvincibleState)state).nextState(player);
        } else if (state instanceof InvisibleState) {
            ((InvisibleState)state).nextState(player);
        }
    }

    /**
     * Checks if a move can be made
     * 
     * @param entity
     * @return boolean
     */
    public boolean checkMove(Entity entity) {
        for (Entity e : entities) {
            if (e instanceof Door) {
                int keyId = ((Door) e).getKeyId();
                Key key = this.player.getKeyInInventory(keyId); 

                // if the player has a key and the door(closed) is in the same position, move the player
                if (e.isAtSamePosition(entity) && this.player.getInventory().contains(key)) {
                    return true;
                }
            }

            if (e.isAtSamePosition(entity) && e.getCollision()) {
                return false;
            }
        }

        return true;
    }
    
    /**
     * checks if the player can move and entities that will be affected after doing so.
     *
     * @param Direction
     */
    public void doPlayerMovement(Direction movementDirection) {
        Player player = getPlayer();
        Boulder b = new Boulder();
        Position newPos = player.getPosition().translateBy(movementDirection);
        Position boulderPos = player.getPosition().translateBy(movementDirection).translateBy(movementDirection);

        ArrayList<Entity> entitiesInPos = getAllEntitiesInPosition(newPos.getX(), newPos.getY());
        boolean playerCanMove = true;
        boolean boulderCanMove = true;
        for (Entity e : entitiesInPos) {
            if (e.getCollision()) {
                if (e instanceof Boulder) {
                    ArrayList<Entity> boulderCheck = getAllEntitiesInPosition(boulderPos.getX(), boulderPos.getY());
                    for (Entity e2 : boulderCheck) {
                        if (e2.getCollision()) {
                            boulderCanMove = false;
                            playerCanMove = false;
                            break;
                        }
                    }
                    b = (Boulder) e;
                } else if (e instanceof Door) {
                    int keyId = ((Door) e).getKeyId();
                    if (this.player.getKeyInInventory(keyId) == null) {
                        playerCanMove = false;
                        break;
                    }
                    
                    Key key = this.player.getKeyInInventory(keyId); 
                    if (e.getPosition().equals(newPos) && this.player.getInventory().contains(key)) {
                        key.consumedByPlayer(player);
                        Door d = (Door) e;
                        d.setDoorOpen();
                    }
                } else {
                    playerCanMove = false;
                }
                break;
                
            } else if (e instanceof Portal) {
                for (Entity e3 : getEntities()) {
                    if (e3 instanceof Portal) {
                        Portal portalCheck = (Portal) e3;
                        Portal thisPortal = (Portal) e;
                        if (portalCheck.getColour().equals(thisPortal.getColour()) && !portalCheck.equals(thisPortal)) {
                            newPos = portalCheck.getPosition();
                            break;
                        }
                    }
                }
            } 
        }

        if (playerCanMove && boulderCanMove) {
            b.setPosition(boulderPos);
            activateSwitch(b);
            player.setPosition(newPos);
        } else if (playerCanMove) {
            player.setPosition(newPos);
        }
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

    public boolean boulderInPosition(Position position) {
        return entities.stream().filter(e -> e instanceof Boulder).anyMatch(b -> b.getPosition().equals(position));
    }

    public void spawnEnemies(int tickCount) {
        int spiderRate = configs.getInt("spider_spawn_rate");
        
        if (spiderRate != 0 && tickCount % spiderRate == 0) {
            Position newSpiderPos = generateSpiderPos();

            // Spider cant spawn on boulders
            while (boulderInPosition(newSpiderPos)) {
                newSpiderPos = generateSpiderPos();
            }
            entities.add(new Spider(newSpiderPos.getX(), newSpiderPos.getY(), "spider", this, configs.getInt("spider_attack"), configs.getInt("spider_health")));
        }
    }

    public Position generateSpiderPos() {
        Random random = new Random();
        // Bounding the range using random.nextInt(max - min + 1) + min
        int randomX = random.nextInt(25 - (1) + 1) + (1);
        int randomY = random.nextInt(25 - (1) + 1) + (1);

        return new Position(randomX, randomY);
    }
}