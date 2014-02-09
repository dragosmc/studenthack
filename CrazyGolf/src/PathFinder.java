import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class PathFinder {
    private static final int BOUNCES = 15;
    private List<Vector> vectors;
    private Collisionable previous = null;
    private int count;

    public PathFinder() {
        vectors = new LinkedList<Vector>();
    }

    public void run(Vector v) {
        count = BOUNCES;
        Vector tmpVec = v;
        vectors.clear();

        List<Collisionable> collisions = new LinkedList<Collisionable>();

        resolve(collisions);

        while (count-- > 0) {
            if (tmpVec != null) {
                vectors.add(tmpVec);
                tmpVec = _run(tmpVec, collisions);
            } else {
                break;
            }
        }
    }

    private void resolve(List<Collisionable> collisions) {
        for (Drawable drawable : Main.app.core.getDrawables()) {
            if (drawable instanceof Primitive) {
                Primitive primitive = (Primitive) drawable;
                primitive.resolve(collisions);
            }
        }
    }

    private Vector _run(Vector v, List<Collisionable> collisions) {
        double distance = Double.POSITIVE_INFINITY;
        Collisionable C = null;

        for (Collisionable collision : collisions) {
            if (collision != previous) {
                double d;
                if ((d = collision.collidesAfter(v)) != Double.NaN) {
                    if (d < distance) {
                        distance = d;
                        C = collision;
                    }
                }
            }
        }
        previous = C;
        if (C != null) {
            v.setDir(v.dx * distance, v.dy * distance);
            if (C instanceof Segment && ((Segment) C).isEndPoint()) {
                return null;
            }
            return C.bounce(v, 1);
        }
        return null;
    }

    public void draw(Graphics2D g) {
        Color c = g.getColor();
        Stroke s = g.getStroke();
        g.setColor(Color.blue);
        g.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 2, new float[]{10, 10}, 0));
        for (Vector vector : vectors) {
            g.drawLine((int) vector.x, (int) vector.y, (int) vector.x + (int) vector.dx, (int) vector.y + (int) vector.dy);
        }
        g.setColor(c);
        g.setStroke(s);
    }

    public int getBounces() {
        return BOUNCES - count - 1;
    }
}
