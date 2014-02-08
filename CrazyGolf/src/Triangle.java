import java.awt.*;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Triangle implements Primitive, Drawable, Dropable {
    private Point centre;
    private Point corner;

    @Override
    public void resolve(List<Collisionable> list) {
    }

    @Override
    public void draw(Graphics2D g) {

    }

    @Override
    public void update() {

    }
}
