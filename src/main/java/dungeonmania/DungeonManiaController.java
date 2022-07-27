package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;
import dungeonmania.util.Position;
import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.battles.Battle;
import dungeonmania.entities.collectable.*;
import dungeonmania.entities.moving.*;
import dungeonmania.entities.staticEntity.Boulder;
import dungeonmania.entities.staticEntity.Portal;

import java.util.ArrayList;
import java.util.List;


public class DungeonManiaController {
    private Dungeon dungeon;

    public String getSkin() {
        return "default";
    }

    public String getLocalisation() {
        return "en_US";
    }

    /**
     * /dungeons
     */
    public static List<String> dungeons() {
        return FileLoader.listFileNamesInResourceDirectory("dungeons");
    }

    /**
     * /configs
     */
    public static List<String> configs() {
        return FileLoader.listFileNamesInResourceDirectory("configs");
    }

    /**
     * /game/new
     */
    public DungeonResponse newGame(String dungeonName, String configName) throws IllegalArgumentException {
        String dungeonMap = "";
        String configs = "";

        try {
            dungeonMap = FileLoader.loadResourceFile("/dungeons/" + dungeonName + ".json");
            configs = FileLoader.loadResourceFile("/configs/" + configName + ".json");
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
        
        this.dungeon = new Dungeon(dungeonMap, configs);
        this.dungeon.setName(dungeonName);

        List<EntityResponse> entities = new ArrayList<>();
        for (Entity e : dungeon.getEntities()) {
            entities.add(e.getEntityResponse());
        }

        List<ItemResponse> inventory = new ArrayList<>();
        List<BattleResponse> battles = new ArrayList<>();
        List<String> buildables = new ArrayList<>();

        return new DungeonResponse(dungeon.getId(), dungeonName, entities, inventory, battles, buildables, dungeon.getGoals());
    }

    /**
     * /game/dungeonResponseModel
     */
    public DungeonResponse getDungeonResponseModel() {
        List<EntityResponse> entities = new ArrayList<>();
        for (Entity e : dungeon.getEntities()) {
            entities.add(e.getEntityResponse());
        }

        Inventory currInventory = dungeon.getPlayer().getInventory();
        List<ItemResponse> inventory = new ArrayList<>();
        for (CollectableEntity c : currInventory.getCollections()) {
            inventory.add(c.getItemResponse());
        }

        List<BattleResponse> battles = new ArrayList<>();
        for (Battle b : dungeon.getBattles()) {
            battles.add(b.getBattleResponse());
        }

        List<String> buildables = new ArrayList<>();
        if (currInventory.hasEnoughMaterialsToCraft("bow")) {
            buildables.add("bow");
        }
        if (currInventory.hasEnoughMaterialsToCraft("shield")) {
            buildables.add("shield");
        }

        
        return new DungeonResponse(dungeon.getId(), dungeon.getName(), entities, inventory, battles, buildables, dungeon.getGoals());
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        // TODO: player uses item for each tick
        return getDungeonResponseModel();
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        Player player = dungeon.getPlayer();
        Boulder b = new Boulder();
        Position newPos = player.getPosition().translateBy(movementDirection);
        Position boulderPos = player.getPosition().translateBy(movementDirection).translateBy(movementDirection);

        ArrayList<Entity> entitiesInPos = dungeon.getAllEntitiesInPosition(newPos.getX(), newPos.getY());
        boolean playerCanMove = true;
        boolean boulderCanMove = true;
        for (Entity e : entitiesInPos) {
            if (e.getCollision()) {
                if (e.getType().equals("boulder")) {
                    ArrayList<Entity> boulderCheck = dungeon.getAllEntitiesInPosition(boulderPos.getX(), boulderPos.getY());
                    for (Entity e2 : boulderCheck) {
                        if (e2.getCollision()) {
                            boulderCanMove = false;
                            break;
                        }
                    }
                    b = (Boulder) e;
                }
                if (boulderCanMove) {
                    break;
                } else {
                    playerCanMove = false;
                }
            } else if (e.getType().equals("portal")) {
                for (Entity e3 : dungeon.getEntities()) {
                    if (e3 instanceof Portal) {
                        Portal portalCheck = (Portal) e3;
                        Portal thisPortal = (Portal) e;
                        if (portalCheck.getColour().equals(thisPortal.getColour()) && !portalCheck.equals(thisPortal)) {
                            newPos = portalCheck.getPosition();
                        }
                    }
                }
            }
        }

        if (playerCanMove && boulderCanMove) {
            b.setPosition(boulderPos);
            dungeon.activateSwitch(b);
            player.setPosition(newPos);
        } else if (playerCanMove) {
            player.setPosition(newPos);
        }
        dungeon.getAllMovingEntitiesButPlayer().forEach(e -> e.move(movementDirection));
        dungeon.doBattles();
        dungeon.pickUpItem();
        dungeon.updateGoal();
        return getDungeonResponseModel();
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        if (!buildable.equals("bow") && !buildable.equals("shield")) {
            throw new IllegalArgumentException();
        }
        Inventory inventory = dungeon.getPlayer().getInventory();
        if (!inventory.hasEnoughMaterialsToCraft(buildable)) {
            throw new InvalidActionException("You don't have enough materials to craft " + buildable + "!");
        }
        
        inventory.build(buildable);

        return getDungeonResponseModel();
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        if (dungeon.getEntityById(entityId) == null) {
            throw new IllegalArgumentException();
        }
        Entity entity = dungeon.getEntityById(entityId);
        
        if (entity.getType().equals("mercenary")) {
            // TODO: bribe the mercenary
        } else if (entity.getType().equals("zombie_toast_spawner")) {
            // TODO: attack the zombie toast spawner
        } else {
            throw new InvalidActionException("You can't interact with " + entity.getType() + "!");
        }
        return getDungeonResponseModel();
    }

    /**
     * /game/save
     */
    public DungeonResponse saveGame(String name) throws IllegalArgumentException {
        return null;
    }

    /**
     * /game/load
     */
    public DungeonResponse loadGame(String name) throws IllegalArgumentException {
        return null;
    }

    /**
     * /games/all
     */
    public List<String> allGames() {
        return new ArrayList<>();
    }

}
