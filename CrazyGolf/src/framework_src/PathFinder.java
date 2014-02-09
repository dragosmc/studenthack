package framework_src;

import collisionable_src.Segment;
import collisionable_src.Vector;
import interface_src.Collisionable;
import interface_src.Drawable;
import interface_src.Primitive;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class PathFinder {
    private static int BOUNCES = 15;
    private List<Vector> vectors;
    private Collisionable previous = null;
    private int count;
    private boolean draw = true;

    public List<Vector> getVectors() {
        return vectors;
    }

    public void setVectors(List<Vector> vectors) {
        this.vectors = vectors;
    }

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
            v.setDir(v.getDx() * distance, v.getDy() * distance);
            if (C instanceof Segment && ((Segment) C).isEndPoint()) {
                return null;
            }
            return C.bounce(v, 1);
        }
        return null;
    }

    public void draw(Graphics2D g) {
        if (draw) {
            Color c = g.getColor();
            Stroke s = g.getStroke();
            g.setColor(Color.DARK_GRAY);
            g.setStroke(new BasicStroke(1f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 2, new float[]{10, 10}, 0));
            for (Vector vector : vectors) {
                g.drawLine((int) vector.getX(), (int) vector.getY(), (int) vector.getX() + (int) vector.getDx(),
                        (int) vector.getY() + (int) vector.getDy());
            }
            g.setColor(c);
            g.setStroke(s);
        }
    }

    public int getBounces() {
        return BOUNCES - count - 1;
    }

    public void setBounces(int bounces) {
        BOUNCES = bounces;
    }

    public void setDraw(boolean d) {
        draw = d;
    }
}
