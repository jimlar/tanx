package tanx.model;

import java.awt.*;

public interface MoveListener {
    void moved(GameObject object, Rectangle oldBounds, Rectangle newBounds);
}
