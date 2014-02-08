import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Triangle implements Primitive, Drawable, Dropable {
    private Point centre;
    private Point corner;
    private final ArrayList<dPoint> template =  new ArrayList<dPoint>();

    public Triangle() {
        template.add(new dPoint(-1,0));
        template.add(new dPoint(1,0));
        template.add(new dPoint(0,1));
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
            double x = i.getX() * centreVector.getDir().getX() - i.getY() * centreVector.getDir().getY() + centre.getX();
            double y = i.getX() * centreVector.getDir().getX() + i.getY() * centreVector.getDir().getY() + centre.getY();


            list.add(new dPoint(x, y));
            tempList.add(new dPoint(x, y));
        }
        for (int i = 0; i < 3; i++) {
            Segment tempSegment = new Segment();
            tempSegment.setLocation(tempList.get(i).getX(), tempList.get(i).getY());
            if (i != 2) {
                tempSegment.setDir(tempSegment.getX() - tempList.get(i + 1).getX(), tempSegment.getY() - tempList.get(i + 1).getY());
            } else {
                tempSegment.setDir(tempSegment.getX() - tempList.get(0).getX(), tempSegment.getY() - tempList.get(0).getY());
            }
            list.add(tempSegment);


        }


    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }
}
