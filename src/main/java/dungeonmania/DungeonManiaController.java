package dungeonmania;

import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.*;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.moving.Inventory;

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
        return null;
    }

    /**
     * /game/tick/item
     */
    public DungeonResponse tick(String itemUsedId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    /**
     * /game/tick/movement
     */
    public DungeonResponse tick(Direction movementDirection) {
        return null;
    }

    /**
     * /game/build
     */
    public DungeonResponse build(String buildable) throws IllegalArgumentException, InvalidActionException {
        if (!buildable.equals("bow") && !buildable.equals("shield")) {
            throw new IllegalArgumentException();
        }
        Inventory inventory = dungeon.getPlayer().getInventory();
        if (inventory.hasEnoughMaterialsToCraft(buildable)) {
            throw new InvalidActionException("You don't have enough materials to craft " + buildable + "!");
        }
        
        inventory.build(buildable);
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }
}
