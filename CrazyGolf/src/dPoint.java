/**
 * Created by Adam Bedford on 08/02/14.
 */
public class dPoint implements Collisionable {
    boolean endPoint = false;
    double x, y;

    public dPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public double collidesAfter(Vector v) {
        return Double.NaN;
    }


    @Override
    public Vector bounce(Vector v, double l) {
        if (endPoint) {
            Vector vector = new Vector();
            vector.setLocation(this.x,this.y);
            vector.setDir(0,0);
            return vector;
        }

        return null;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public String toString() {
        return String.format("dPoint [%f, %f]", x, y);
    }

}
