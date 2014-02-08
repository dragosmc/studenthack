import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class applet extends Applet {
    public Core core;

    public applet(){
        core = new Core();
    }
    @Override
    public void update(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        paint(image.getGraphics());
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D)g).setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        for (Drawable drawable : core.getDrawables()) {
            drawable.draw((Graphics2D) g);
        }
    }

    public void removeMouseListeners(DrawableListener dl){
        this.removeMouseMotionListener(dl);
        this.removeMouseListener(dl);
    }

    public void addMouseListeners(DrawableListener dl) {

        MouseListener[] mouseListeners = this.getMouseListeners();
        for (MouseListener mouseListener : mouseListeners) {
            this.removeMouseListener(mouseListener);
        }

        MouseMotionListener[] mouseMotionListeners = this.getMouseMotionListeners();
        for (MouseMotionListener mouseListener : mouseMotionListeners) {
            this.removeMouseMotionListener(mouseListener);
        }

        this.addMouseMotionListener(dl);
        this.addMouseListener(dl);

    }
}
