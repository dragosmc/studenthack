/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Vector {
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

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
    public dPoint getDir(){
        return new dPoint(dx,dy);
    }
}
