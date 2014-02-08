/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Segment extends Vector implements Collisionable {
    @Override
    public double collidesAfter(Vector v) {
        double qX = v.getX() - x;
        double qY = v.getY() - y;

        double uDx = v.getDir().getX();
        double uDy = v.getDir().getY();
        double vDx = dx;
        double vDy = dy;

        double det = vDx * uDy - vDy * uDx;

        if (det == 0) return Double.NaN;

        double beta = -(uDx * qY - uDy * qX) / det;
        double alpha = (vDy * qX - vDx * qY) / det;

        System.out.println(String.format("%f, %f", alpha, beta));

        if (beta < 0 || beta > 1 || alpha < 0) return Double.NaN;

        return alpha;
    }

    @Override
    public Vector bounce(Vector v, double l) {
        double Rx = v.x + v.dx * l;
        double Ry = v.y + v.dy * l;

        double det = dx * dx + dy * dy;
        if(det == 0) return null;

        double alpha = (v.dx * dx + v.dy * dy) / det;
        double beta = (v.dx * -dy + v.dy * dx) / det;

        Vector tmp = new Vector();
        tmp.setLocation(Rx, Ry);
        tmp.setDir(v.dx + 2 * beta * dy, v.dy - 2 * beta * dx);

        return tmp;
    }

    public String toString() {
        return String.format("Segment [%f, %f]-[%f, %f]", x, y, dx, dy);
    }
}
