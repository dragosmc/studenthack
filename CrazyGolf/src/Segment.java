/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Segment extends Vector implements Collisionable {
    @Override
    public double collidesAfter(Vector v) {
        double qX = v.getX() - x;
        double qY = v.getY() - y;

        double vDx = v.getDir().getX();
        double vDy = v.getDir().getY();
        double uDx = dx;
        double uDy = dy;

        double det = vDx * uDy - vDy * uDx;

        if (det == 0) return Double.NaN;

        double beta = (vDx * qY - vDy * qX) / det;
        double alpha = (uDx * qY - uDy * qX) / det;

        System.out.println(String.format("%f, %f", alpha, beta));

        if (beta < 0 || beta > 1) return Double.NaN;

        return alpha;
    }

    @Override
    public Vector bounce(Vector v, double l) {
        return null;
    }

    public String toString(){
        return String.format("Segment [%f, %f]-[%f, %f]", x, y, dx, dy);
    }
}
