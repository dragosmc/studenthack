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
    private int clickCount;
    private Point tmp;

    public DropListener(DropType d) {
        dropType = d;
        clickCount = 0;
    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        switch (dropType) {
            case Rect: {
                if (clickCount == 0) {
                    tmp = new Point(mouseEvent.getX(), mouseEvent.getY());
                    current = new Rect(mouseEvent.getPoint(), tmp);
                    Main.app.core.addDrawable((Drawable) current);
                    clickCount = 1;
                } else {
                    Main.app.removeMouseListeners(this);

                    clickCount = 0;
                }
            }
            break;
            case Triangle: {
                if (clickCount == 0) {
                    tmp = new Point(mouseEvent.getX(), mouseEvent.getY());
                    current = new Triangle(mouseEvent.getPoint(), tmp);
                    Main.app.core.addDrawable((Drawable) current);
                    clickCount = 1;
                } else {
                    Main.app.removeMouseListeners(this);

                    clickCount = 0;
                }
            }
            break;
            case Ball: {

            }
            break;
            case Hole: {

            }
            break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        if (clickCount > 0) {
            tmp.setLocation(mouseEvent.getX(), mouseEvent.getY());
            Main.app.repaint();
        }
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
