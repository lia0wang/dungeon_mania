package dungeonmania.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import dungeonmania.util.FileLoader;

public class Dungeon {
    private String name;
    private ArrayList<Entity> entities;
    private ArrayList<String> goals;
    
    public Dungeon(String name, String mapName) {
        this.name = name;

        // the check for a valid map would need to be done in the controller,
        // so this JSONObject may need to be created there
        try {
            JSONObject configuration = new JSONObject(FileLoader.loadResourceFile("/resource/" + mapName + ".json"));
        } catch (Exception e) {
            
        }
        
        this.populate(entities, configuration);
    }

    public void populate(ArrayList<Entity> entities, JSONObject configuration) {
        JSONArray allEntities = configuration.getJSONArray("entities");
        for (Object e : allEntities) {

            JSONObject currEntity = (JSONObject) e;
            int x = currEntity.getInt("x");
            int y = currEntity.getInt("y");
            String type = currEntity.getString("type");

            if (type.equals("portal")) {
                // get the colour and make entity
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
            if (e.isAtSamePosition(entity)) {
                return false;
            }
        }
        return true;
    }
}
