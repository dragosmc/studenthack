import java.awt.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Math.sqrt;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class PathFinder {
    private static final int BOUNCES = 10;
    private List<Vector> vectors;
    private Collisionable previous = null;

    public PathFinder() {
        vectors = new LinkedList<Vector>();
    }

    public void run(Vector v) {
        int cnt = BOUNCES;
        Vector tmpVec = v;
        vectors.clear();

        List<Collisionable> collisions = new LinkedList<Collisionable>();

        resolve(collisions);

        while (cnt-- > 0) {
            if (tmpVec != null) {
                vectors.add(tmpVec);
                tmpVec = _run(tmpVec, collisions);
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
            if (C instanceof Segment && ((Segment) C).isEndPoint()) {
                v.setDir(v.dx * distance, v.dy * distance);
                return null;
            }
            v.setDir(v.dx * distance, v.dy * distance);
            return C.bounce(v, 1);
        }

        return null;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.blue);
        for (Vector vector : vectors) {
            g.drawLine((int) vector.x, (int) vector.y, (int) vector.x + (int) vector.dx, (int) vector.y + (int) vector.dy);
        }
        g.setColor(c);
    }
}
