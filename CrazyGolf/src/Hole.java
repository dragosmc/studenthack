import java.awt.*;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Hole implements Drawable, Dropable, Primitive {
    private final Point point;
    private final Rect rect;

    public Hole(Point point) {
        this.point = point;
        this.rect = new Rect(point,new Point(point.x + 5,point.y + 5));

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.fillOval(point.x,point.y,10,10);

    }

    @Override
    public void drawGL() {

    }

    @Override
    public void resolve(List<Collisionable> list) {
        rect.setEndPoint();
        rect.resolve(list);
    }
}
