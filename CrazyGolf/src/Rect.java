import java.awt.*;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Rect implements Primitive {
    public Point centre;
    public Point corner;

    @Override
    public List<Collisionable> resolve() {
        return null;
    }
}
