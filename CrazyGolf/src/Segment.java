/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Segment extends Vector implements Collisionable {
    @Override
    public double collidesAfter(Vector v) {
        return 0;
    }

    @Override
    public Vector bounce(Vector v, double l) {
        return null;
    }
}
