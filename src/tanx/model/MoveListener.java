package tanx.model;

import java.awt.*;

public interface MoveListener {
    void moved(GameObject object, int direction, MapPosition oldPosition);
}
