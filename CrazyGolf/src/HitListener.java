import com.sun.prism.*;

import java.awt.*;
import java.awt.BasicStroke;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

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
            //Stroke s = g.getStroke();
            //g.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 0, new float[]{10, 10}, 0));
            g.drawLine(ballLocation.x, ballLocation.y, mouseLocationPoint.x, mouseLocationPoint.y);
            //g.setStroke(s);

        }

        @Override
        public void drawGL() {

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

    }

    private void setDirectionAndRun(MouseEvent mouseEvent) {
        Vector v = new Vector();
        double Ballx = ghostPoint.ballLocation.getX();
        double Bally = ghostPoint.ballLocation.getY();
        v.setLocation(Ballx, Bally);
        v.setDir(mouseEvent.getX() - Ballx, mouseEvent.getY() - Bally);
        Main.app.core.getPathFinder().run(v);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (ghostPoint != null) {
            ghostPoint.mouseLocationPoint.setLocation(mouseEvent.getX(), mouseEvent.getY());
            Main.app.repaint();
        }

    }
}
