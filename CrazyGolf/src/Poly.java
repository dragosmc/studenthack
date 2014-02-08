import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Poly implements Primitive {
    public int n; //number of points in the polygon
    public int x[]; //x coordinates
    public int y[]; //y coordinates

    @Override
    public void resolve(List<Collisionable> list) {
        for (int i = 0; i < n; i++) {
            list.add(new dPoint(x[i], y[i]));
        }

        for (int i = 0; i < n; i++) {
            Segment s = new Segment();
            s.setLocation(x[i], y[i]);
            if (i < n - 1) {
                s.setDir(x[i + 1] - x[i], y[i + 1] - y[i]);
            } else {
                s.setDir(x[0] - x[i], y[0] - y[i]);
            }
        }

    }
}
