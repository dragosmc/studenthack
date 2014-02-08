import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class DropListener extends DrawableListener {
    private DropType dropType;
    private Dropable current;

    public DropListener(DropType d) {
        dropType = d;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (dropType) {
            case Rect: {

            }
            break;
            case Triangle: {

            }
            break;
            case Ball: {

            } break;
            case Hole: {

            }break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

    public enum DropType {
        Ball,
        Hole,
        Rect,
        Triangle
    }

    public void setDropType(DropType d) {
        dropType = d;
    }


}
