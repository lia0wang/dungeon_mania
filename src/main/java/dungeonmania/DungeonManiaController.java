package dungeonmania;

import dungeonmania.entities.Dungeon;
import dungeonmania.entities.Entity;
import dungeonmania.entities.EntityFactory;
import dungeonmania.exceptions.InvalidActionException;
import dungeonmania.response.models.DungeonResponse;
import dungeonmania.util.Direction;
import dungeonmania.util.FileLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class DungeonManiaController {
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

        return null;
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
        return null;
    }

    /**
     * /game/interact
     */
    public DungeonResponse interact(String entityId) throws IllegalArgumentException, InvalidActionException {
        return null;
    }

    public static Dungeon createNewDungeon(String path) throws JsonSyntaxException, IOException {
        List<Entity> entities = new ArrayList<>();
        EntityFactory entityFactory = new EntityFactory();

        JsonObject test = JsonParser.parseString(FileLoader.loadResourceFile(path)).getAsJsonObject();
        JsonArray jsonEntities = (JsonArray) test.get("entities");
        for (JsonElement e : jsonEntities) {
            JsonObject entityObj = e.getAsJsonObject();
            String type = entityObj.get("type").toString().replaceAll("\"", "");
                String intX = entityObj.get("x").toString();
                String intY = entityObj.get("y").toString();
                Entity newEntity = entityFactory.getEntity(type, Integer.parseInt(intX), Integer.parseInt(intY));
                entities.add(newEntity);
        }
        return new Dungeon(entities);
    }

    public static void main(String args[]) throws IOException {
        JsonObject test = JsonParser.parseString(FileLoader.loadResourceFile("dungeons/test_createNewDungeon.json")).getAsJsonObject();
        JsonArray entities = (JsonArray) test.get("entities");
        for (JsonElement e : entities) {
            System.out.println(e);
        }
        Dungeon dungeon = createNewDungeon("dungeons/test_createNewDungeon.json");
        System.out.println(dungeon.getEntities());
    }   
}
