package framework_src;

import collisionable_src.Vector;
import drawable_src.Ball;
import framework_src.CrazyApplet;
import interface_src.Drawable;
import listener_src.DrawableListener;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.ListIterator;

/**
 * Created with IntelliJ IDEA.
 * User: Liam
 * Date: 09/02/14
 * Time: 04:39
 * To change this template use File | Settings | File Templates.
 */
public class PlayMode implements Runnable {

    private int fps = 10;
    private int skipTicks = 1000 / fps;
    private List<Vector> vectorList;

    public PlayMode(List<Vector> vectors) {
        vectorList = vectors;

    }

    public void run() {

        long currentTick = System.currentTimeMillis();
        boolean isRunning = true;
        int sleepTime = 0;
        double offset = 2;
        Ball ball = new Ball(new Point(0, 0));
       // Main.app.core.addDrawable(ball);
        Vector v = null;
        ListIterator<Vector> iterator = vectorList.listIterator();
        while (isRunning) {
            Main.app.repaint();
            if (offset > 1) {
                if (iterator.hasNext()) {
                    offset = 0;
                    v = iterator.next();
                } else {
                    isRunning = false;
                    break;
                }
            }

            double xBall = v.getX() + v.getDx() * offset;
            double yBall = v.getY() + v.getDy() * offset;

            offset+=0.1;


            ball.setLocation(xBall, yBall);

            ball.draw((Graphics2D)Main.app.getGraphics());

            currentTick += skipTicks;
            sleepTime = (int) (currentTick - (int) System.currentTimeMillis());
            if (sleepTime >= 0) {
                try {
                    Thread.sleep(sleepTime);
                    Main.app.paint(Main.app.getGraphics());
                } catch (InterruptedException e) {

                    e.printStackTrace();
                }
            }

        }
    }
}
