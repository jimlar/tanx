package tanx.view;

import tanx.model.*;

import java.awt.*;
import java.util.Iterator;

public class MapView extends Canvas implements MapChangeListener {
    private GameMap map;
    private Dimension bufferDimensions;
    private Image bufferImage;
    /* The pixel size of a map block (both x and y ways) */
    public static final int MAP_BLOCK_SIZE = 10;

    public MapView(final GameMap map) {
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

        paintBlocks(bufferGraphics);
        paintObjects(bufferGraphics);

        g.drawImage(bufferImage, 0, 0, null);
    }

    public Dimension getPreferredSize() {
        return new Dimension(getPixelWidth(), getPixelHeight());
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

    private int getPixelHeight() {
        return map.getHeight() * MapView.MAP_BLOCK_SIZE;
    }

    private int getPixelWidth() {
        return map.getWidth() * MapView.MAP_BLOCK_SIZE;
    }

    private void paintBlocks(Graphics g) {

        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                MapBlock block = map.getBlockAt(x, y);
                paintBlock(block, g, x, y);
            }
        }
    }

    private void paintBlock(MapBlock block, Graphics g, int x, int y) {
        if (block.getType() == MapBlock.TYPE_WALL) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.white);
        }

        g.fillRect(x * MapView.MAP_BLOCK_SIZE,
                   y * MapView.MAP_BLOCK_SIZE,
                   (x + 1) * MapView.MAP_BLOCK_SIZE,
                   (y + 1) * MapView.MAP_BLOCK_SIZE);
    }

    private void paintObjects(Graphics g) {
        for (Iterator i = map.getObjects().iterator(); i.hasNext();) {
            GameObject gameObject = (GameObject) i.next();

            GameObjectView.paintObject(gameObject, g);
        }
    }
}
