package tanx.ui;

import tanx.model.*;

import java.awt.*;

public class GameCanvas extends Canvas implements MapChangeListener {
    private GameMap map;
    private Dimension bufferDimensions;
    private Image bufferImage;

    public GameCanvas(final GameMap map) {
        this.map = map;
        map.addMapChangeListener(this);
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {

        /* Recreate buffer if size changes */
        if (bufferDimensions == null
                || bufferImage == null
                || !bufferDimensions.equals(getPreferredSize()))
        {
            this.bufferDimensions = getPreferredSize();
            this.bufferImage = createImage(bufferDimensions.width, bufferDimensions.height);
        }

        Graphics bufferGraphics = bufferImage.getGraphics();
        map.paint(bufferGraphics);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(map.getPixelWidth(), map.getPixelHeight());
    }

    public void moved(GameObject object, int direction, MapPosition oldPosition) {
        repaint();
    }

    public void added(GameObject object) {
        repaint();
    }

    public void removed(GameObject object) {
        repaint();
    }
}
