package dungeonmania.staticEntity;

import dungeonmania.StaticEntity;

public abstract class Exit extends StaticEntity{
    private final String type = "exit";
    private String id;

    public Exit(int x, int y, int hasCollision, String id) {
        super(x, y, hasCollision);
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void interact() {}

    public String getId() {
        return id;
    }

}
