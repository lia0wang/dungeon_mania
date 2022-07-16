package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.util.FileLoader;

public class Dungeon {
    private String name;
    private ArrayList<Entity> entities;
    private ArrayList<Entity> enemies;
    private ArrayList<String> goals;
    private HashMap<String, Integer> collectables;
    
    public Dungeon(String name, String mapName) {
        this.name = name;

        // the check for a valid map would need to be done in the controller,
        // so this JSONObject may need to be created there
        // will need try & catch for exception handling
        
        // JSONObject configuration = new JSONObject(FileLoader.loadResourceFile("/resource/" + mapName + ".json"));
        // this.populate(entities, configuration);
    }

    public void populate(ArrayList<Entity> entities, JSONObject configuration) {
        JSONArray allEntities = configuration.getJSONArray("entities");
        for (Object e : allEntities) {

            JSONObject currEntity = (JSONObject) e;
            int x = currEntity.getInt("x");
            int y = currEntity.getInt("y");
            String type = currEntity.getString("type");

            if (type.equals("portal")) {
                String colour = currEntity.getString("colour");

            } else if (type.equals("door")) {

            } else if (type.equals("key")) {

            } else {
                // maybe more checks
            }
        }
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
            if (e.isAtSamePosition(entity) && e.getCollision()) {
                return false;
            }
        }
        return true;
    }
}
