package tanx.model;

import java.io.Serializable;

public abstract class Command implements Serializable {
    private Integer targetId;

    public Command() {
        this.targetId = null;
    }

    protected Command(GameObject target) {
        setTarget(target);
    }

    public void setTarget(GameObject target) {
        if (target != null) {
            this.targetId = target.getId();
        } else {
            this.targetId = null;
        }
    }

    public GameObject getTarget(GameMap map) {
        if (targetId == null) {
            throw new IllegalStateException("This command has not target");
        }
        return map.getObjectById(targetId);
    }
}
