import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public abstract class DrawableListener implements MouseListener, MouseMotionListener {
    public abstract void draw(Graphics g);

    @Override
    public abstract void mouseClicked(MouseEvent mouseEvent);

    @Override
    public abstract void mousePressed(MouseEvent mouseEvent);

    @Override
    public abstract void mouseReleased(MouseEvent mouseEvent);

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public abstract void mouseMoved(MouseEvent mouseEvent);

}
