import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class PolyDrawListener extends DrawableListener {
    private List<Point> pointList = new LinkedList<Point>();
    private Point currentPoint;
    public boolean drawComplete = true;
    private Poly poly = null;

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        if (poly == null) {
            poly = new Poly();
            poly.addPoint(mouseEvent.getPoint());
            drawComplete = false;
            Main.app.core.addDrawable(poly);
        }
        currentPoint = mouseEvent.getPoint();
        poly.addPoint(currentPoint);
        pointList = new LinkedList<Point>();
        if (mouseEvent.getClickCount() == 2) {
            Main.app.removeMouseListeners(this);
            drawComplete = true;
            poly = null;

        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (!drawComplete) {
            currentPoint.setLocation(mouseEvent.getX(), mouseEvent.getY());
            Main.app.repaint();

        }
    }
}
