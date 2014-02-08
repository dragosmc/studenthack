import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Core {
    // Contains a list of primitives
    private List<Primitive> primitiveList;
    private List<Drawable> drawables;
    private PathFinder pathFinder;

    public Core() {
        primitiveList = new LinkedList<Primitive>();
        drawables = new LinkedList<Drawable>();
    }

    public List<Collisionable> resolvePrimitive() {
        List<Collisionable> tempList = new LinkedList<Collisionable>();
        for (Primitive primitive : primitiveList) {
            primitive.resolve(tempList);
        }
        return tempList;

    }

    public boolean addDrawable(Drawable d) {
        return drawables.add(d);
    }

    public boolean addPrimitive(Primitive p) {
        return primitiveList.add(p);
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }


    public void removeDrawable(Drawable drawable) {
        drawables.remove(drawable);
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }
}
