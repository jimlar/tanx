package tanx.model;

import java.awt.*;

public class Tank extends GameObject {
    public void paint(Graphics g) {
        g.drawRect((int) bounds.getX(), (int) bounds.getY(), 10, 10);
    }
}
