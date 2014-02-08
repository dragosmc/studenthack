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



    }
}
