/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Vector {
    // starting points
    double x;
    double y;

    // direction
    double dx;
    double dy;

    public void setLocation(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setDir(double dx, double dy){
        this.dx = dx;
        this.dy = dy;
    }
}