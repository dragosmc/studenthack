package listener_src;

import collisionable_src.Vector;
import drawable_src.Ball;
import framework_src.Main;
import framework_src.PathFinder;
import framework_src.PlayMode;
import interface_src.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by Dragos on 08/02/14.
 */
public class HitListener extends DrawableListener {

    private class GhostPoint implements Drawable {
        Point ballLocation = null;
        public Point mouseLocationPoint = null;

        public GhostPoint(Point p) {
            ballLocation = p;
            mouseLocationPoint = new Point((int) p.getX(), (int) p.getY());
        }

        @Override
        public void draw(Graphics2D g) {
            if (mouseLocationPoint == null || ballLocation == null) {
                return;
            }
            Stroke s = g.getStroke();
            g.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 2, new float[]{10, 10}, 0));
            g.drawLine(ballLocation.x, ballLocation.y, mouseLocationPoint.x, mouseLocationPoint.y);
            g.setStroke(s);
        }
    }

    Ball theBall = null;
    GhostPoint ghostPoint = null;

    public HitListener() {
        boolean ballFound = false;
        for (Drawable drawable : Main.app.core.getDrawables()) {
            if (drawable instanceof Ball) {
                ballFound = true;
                theBall = (Ball) drawable;
            }
        }
        if (ballFound) {
            ghostPoint = new GhostPoint(theBall.getPoint());
            Main.app.core.addDrawable(ghostPoint);

        } else {
            Main.app.removeMouseListeners(this);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        setDirectionAndRun(mouseEvent);

        Main.app.core.removeDrawable(ghostPoint);
        Main.app.removeMouseListener(this);
        if(Main.playMode){
            PathFinder temp =  Main.app.core.getPathFinder();
            List<Vector> list =  temp.getVectors();
            PlayMode playMode = new PlayMode(list);
            playMode.run();
        }
    }

    private void setDirectionAndRun(MouseEvent mouseEvent) {
        Vector v = new Vector();
        double ballx = ghostPoint.ballLocation.getX();
        double bally = ghostPoint.ballLocation.getY();
        v.setLocation(ballx, bally);
        v.setDir(mouseEvent.getX() - ballx, mouseEvent.getY() - bally);
        PathFinder pathFinder = Main.app.core.getPathFinder();
        pathFinder.run(v);
        Main.getBounceCounter().setText("Bounces: " + pathFinder.getBounces());
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (ghostPoint != null) {
            ghostPoint.mouseLocationPoint.setLocation(mouseEvent.getX(), mouseEvent.getY());
            Main.app.repaint();
        }
    }
}
