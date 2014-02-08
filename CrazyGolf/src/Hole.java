import java.awt.*;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Hole implements Drawable, Dropable {
    private final Point point;

    public Hole(Point point) {
        this.point = point;

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.fillRect(point.x - 3, point.y - 3, 7, 7);

    }

    @Override
    public void drawGL() {

    }
}
