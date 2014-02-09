package drawable_src;

import collisionable_src.Segment;
import collisionable_src.Vector;
import collisionable_src.dPoint;
import interface_src.Collisionable;
import interface_src.Drawable;
import interface_src.Dropable;
import interface_src.Primitive;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Triangle implements Primitive, Drawable, Dropable {
    private Point centre;
    private Point corner;
    private ArrayList<dPoint> template =  new ArrayList<dPoint>();
    Color color = Color.CYAN;

    public Triangle(Point mid, Point corn ) {
        centre = mid;
        corner = corn;

        template.add(new dPoint(0,0.577));
        template.add(new dPoint(0,-0.577));
        template.add(new dPoint(1,0));
    }

    public Point getCorner() {
        return corner;
    }

    public void setCorner(Point corner) {
        this.corner = corner;
    }

    @Override
    public void resolve(List<Collisionable> list) {
        Vector centreVector = new Vector();
        ArrayList<dPoint> tempList = new ArrayList<dPoint>();
        centreVector.setDir(centre.getX() - corner.getX(), centre.getY() - corner.getY());
        centreVector.setLocation(centre.getX(), centre.getY());

        for (dPoint i : template) {

            dPoint tempDpoint = getPoint(i.getX(),i.getY());

            list.add(tempDpoint);
            tempList.add(tempDpoint);
        }
        for (int i = 0; i < 3; i++) {
            Segment tempSegment = new Segment();
            tempSegment.setLocation(tempList.get(i).getX(), tempList.get(i).getY());
            if (i < 2) {
                tempSegment.setDir(tempList.get(i + 1).getX() -tempList.get(i).getX(),
                                   tempList.get(i + 1).getY() - tempList.get(i).getY());
            } else {
                tempSegment.setDir(tempList.get(0).getX() - tempList.get(i).getX() ,
                                   tempList.get(0).getY() - tempList.get(i).getY() );
            }
            list.add(tempSegment);
        }
    }

    private dPoint getPoint(double templateX, double templateY) {
        double Ax = centre.getX();
        double ABx = corner.getX() - Ax;
        double Ay = centre.getY();
        double ABy = corner.getY() - Ay;

        double Nx = templateX * ABx - templateY * ABy + Ax;
        double Ny = templateX * ABy + templateY * ABx + Ay;
        return new dPoint(Nx, Ny);
    }

    @Override
    public void draw(Graphics2D g) {
        int[] x = new int[4];
        int[] y = new int[4];

        for (int i = 0; i < 3; i++) {
            dPoint tmpPoint = getPoint(template.get(i).getX(), template.get(i).getY());
            x[i] = (int) tmpPoint.getX();
            y[i] = (int) tmpPoint.getY();
        }

        Color prevColor = g.getColor();
        g.setColor(color);
        g.fillPolygon(x, y, 3);
        g.setColor(prevColor);
        g.drawPolygon(x, y, 3);
    }

    @Override
    public void update() {

    }
}
