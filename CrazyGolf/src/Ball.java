import java.awt.*;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Ball implements Drawable, Dropable {
    private Point point;

    public Ball(Point point) {
        this.point = point;

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

       g.fillOval(point.x , point.y, 7, 7);

    }

    @Override
    public void drawGL() {

    }

    public Point getPoint() {
        return point;
    }
    public boolean moveBall(Point offsetVector){
      if (offsetVector.getX() <=point.getX()+ 5+offsetVector.getX() && offsetVector.getY() <=5+offsetVector.getX()  ){
          point = new Point((int)(point.getX()+ 5*offsetVector.getX()),(int)(point.getY()+ 5*offsetVector.getX()));
          return true;
      }
        else
          return false;

    }
}
