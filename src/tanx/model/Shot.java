package tanx.model;


public class Shot extends GameObject implements Runnable {
    private static final int MILLIS_PER_BLOCK = 100;

    private int direction;

    public Shot(MapPosition position, int direction) {
        setMapPosition(position);
        this.direction = direction;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {
        try {
            boolean destroyed = false;
            while (!destroyed) {
                Thread.sleep(MILLIS_PER_BLOCK);
                destroyed = !getMap().execute(new MoveCommand(this, direction));
            }
        } catch (InterruptedException e) {
            System.out.println("Shot thread interrupted");
        }
        getMap().remove(this);
    }

    public int getDirection() {
        return this.direction;
    }
}
