/**
 * Created by Adam Bedford on 08/02/14.
 */
public class dPoint implements Collisionable {

    double x,y;

    public dPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double collidesAfter(Vector v) {
        return 0;
    }

    @Override
    public Vector bounce(Vector v, double l) {
        return null;
    }
}
