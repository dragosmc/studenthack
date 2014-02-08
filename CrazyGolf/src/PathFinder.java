import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class PathFinder {
    private static final int BOUNCES = 1;


    public void run(Vector v) {
        int cnt = BOUNCES;
        Vector tmpVec = v;

        System.out.println(String.format("%f, %f - %f, %f", v.x, v.y, v.dx, v.dy));

        List<Collisionable> collisions = new LinkedList<Collisionable>();

        resolve(collisions);
        for (Collisionable collision : collisions) {
            System.out.println(collision.toString());
        }

        while (cnt-- > 0) {
            if(tmpVec != null)
                tmpVec = _run(tmpVec, collisions);
            else
                System.out.println("No Collisions");
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
            double d;
            if ((d = collision.collidesAfter(v)) != Double.NaN) {
                if (d < distance) {
                    distance = d;
                    C = collision;
                }
                System.out.println(distance);
            } else {
                System.out.println("NaN");
            }
        }

        if (C != null) {
            System.out.println(String.format("%f", distance));
            //return C.bounce(v, distance);
        }

        return null;
    }
}
