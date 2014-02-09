package interface_src;

import collisionable_src.Vector;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public interface Collisionable {
    public double collidesAfter(Vector v);
    public Vector bounce(Vector v, double l);


}
