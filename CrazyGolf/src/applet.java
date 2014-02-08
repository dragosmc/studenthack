import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class applet extends Applet {
    Core core;

    @Override
    public void update(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        paint(image.getGraphics());
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {

    }
}
