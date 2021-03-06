package game.bases;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Nttung PC on 7/16/2017.
 */
public class ImageRenderer {
    public BufferedImage image;

    public ImageRenderer(BufferedImage image) {
        this.image = image;
    }

    public void render(Graphics2D g, Vector2D position){
        g.drawImage(image, (int)position.x - image.getWidth() / 2, (int)position.y - image.getHeight() / 2, null);
    }
}
