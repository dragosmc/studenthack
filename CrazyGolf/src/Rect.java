import java.awt.*;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Rect implements Primitive {
    private Point centre;
    private Point corner;

    @Override
    public void resolve(List<Collisionable> list) {
    }

    public Point getCentre(){
        return centre;
    }

    public Point getCorner(){
        return corner;
    }
}
