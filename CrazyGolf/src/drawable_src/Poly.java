package drawable_src;

import collisionable_src.Segment;
import collisionable_src.dPoint;
import interface_src.Collisionable;
import interface_src.Drawable;
import interface_src.Primitive;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Poly implements Primitive, Drawable {
    public int x[]; //x coordinates
    public int y[]; //y coordinates
    private List<Point> pointList = new LinkedList<Point>();

    @Override
    public void resolve(List<Collisionable> list) {
        for (int i = 0; i < pointList.size(); i++) {
            list.add(new dPoint(x[i], y[i]));
        }

        for (int i = 0; i < pointList.size(); i++) {
            Segment s = new Segment();
            s.setLocation(x[i], y[i]);
            if (i < pointList.size() - 1) {
                s.setDir(x[i + 1] - x[i], y[i + 1] - y[i]);
            } else {
                s.setDir(x[0] - x[i], y[0] - y[i]);
            }

            list.add(s);
        }

    }

    @Override
    public void draw(Graphics2D g) {
        x = new int[pointList.size()];
        y = new int[pointList.size()];
        int i=0;
        for (Point point : pointList) {
            x[i] = (int)point.getX();
            y[i] = (int)point.getY();
            i++;
        }

        g.drawPolygon(x, y, pointList.size());

    }

    @Override
    public void drawGL() {

    }

    public boolean addPoint(Point p) {
        return pointList.add(p);

    }
}
