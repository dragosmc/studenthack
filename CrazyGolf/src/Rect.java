import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Rect implements Primitive, Dropable, Drawable {
    private Point centre;
    private Point midPoint;

    private static final double x[] = {1, -1, -1, 1};
    private static final double y[] = {1, 1, -1, -1};

    //Nx = xABx - yAB + Ax
    //Ny = xABy + yABx + Ay
    //ABx = Bx - Ax
    //ABy = By - Ay
    public Rect(Point centre, Point midPoint) {
        this.centre = centre;
        this.midPoint = midPoint;
    }

    @Override
    public void resolve(List<Collisionable> list) {
        List<dPoint> tempList = new LinkedList<dPoint>();
        for (int i = 0; i <=3; i++) {
            tempList.add(getPoint(x[i], y[i]));
        }

        for (int i = 0; i <=3; i++) {
            Segment s = new Segment();
            s.setLocation(tempList.get(i).getX(), tempList.get(i).getY());
            if (i < 3) {
                s.setDir(tempList.get(i + 1).getX() - tempList.get(i).getX(),
                        tempList.get(i + 1).getY() - tempList.get(i).getY());
            } else {
                s.setDir(tempList.get(i).getX() - tempList.get(0).getX(),
                        tempList.get(i).getY() - tempList.get(0).getY());

            }

            list.add(s);
            list.add(tempList.get(i));

        }

    }

    private dPoint getPoint(double templateX, double templateY) {
        double Ax = centre.getX();
        double ABx = midPoint.getX() - Ax;
        double Ay = centre.getY();
        double ABy = midPoint.getY() - Ay;

        double Nx = templateX * ABx - templateY * ABy + Ax;
        double Ny = templateX * ABy + templateY * ABx + Ay;
        return new dPoint(Nx, Ny);
    }

    public Point getCentre() {
        return centre;
    }

    public Point getMidPoint() {
        return midPoint;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(centre.x - 3, centre.y - 3, 7, 7);

        int[] x = new int[4];
        int[] y = new int[4];

        for (int i = 0; i <=3; i++) {
            dPoint tmpPoint = getPoint(Rect.x[i], Rect.y[i]);
            x[i] = (int) tmpPoint.getX();
            y[i] = (int) tmpPoint.getY();
        }

        g.drawPolygon(x, y, 4);

    }
}
