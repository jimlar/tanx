package tanx.model;

public class MoveCommand extends Command {
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_DOWN = 1;
    public static final int DIRECTION_LEFT = 2;
    public static final int DIRECTION_RIGHT = 3;

    private int direction;

    public MoveCommand(GameObject target, int direction) {
        super(target);
        this.direction = direction;
    }

    /**
     * Create a move command which moves the default (focused) object. Usualy "my" tank.
     */
    public MoveCommand(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
}
