import framework_src.CrazyApplet;
import interface_src.Drawable;
import listener_src.DrawableListener;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Liam
 * Date: 09/02/14
 * Time: 04:39
 * To change this template use File | Settings | File Templates.
 */
public class PlayModeApplet extends CrazyApplet implements Runnable {

    private int fps = 40;
    private int skipTicks = 1000 / fps;



    @Override
    public void update(Graphics g) {
        Image image = createImage(getWidth(), getHeight());
        paint(image.getGraphics());
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON));

        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.black);

        for (Drawable drawable : core.getDrawables()) {
//            if (drawable instanceof Rect) {
//                drawable.drawGL();
//            } else {
            drawable.draw((Graphics2D) g);
//            }
        }


    }

    public void removeMouseListeners(DrawableListener dl) {
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

    public void run() {

        long currentTick = System.currentTimeMillis();
        boolean isRunning = true;
        int sleepTime = 0;

        while (isRunning) {


            currentTick += skipTicks;
            sleepTime = (int) (currentTick - (int) System.currentTimeMillis());
            if (sleepTime >= 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
        // TODO Auto-generated method stub

    }
}
