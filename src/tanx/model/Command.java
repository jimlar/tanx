package tanx.model;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private Integer targetId;

    public Command() {
        this.targetId = null;
    }

    protected Command(GameObject target) {
        this.targetId = target.getId();
    }

    public GameObject getTarget(GameMap map) {
        if (targetId == null) {
            throw new IllegalStateException("This command has not target");
        }
        return map.getObjectById(targetId);
    }
}
