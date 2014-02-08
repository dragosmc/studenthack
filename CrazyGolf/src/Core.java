import java.util.LinkedList;
import java.util.List;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Core {
    // Contains a list of primitives
    private List<Primitive> primitiveList;
    private List<Drawable> drawables;

    public List<Collisionable> resolvePrimitive() {
        List<Collisionable> tempList = new LinkedList<Collisionable>();
        for (Primitive primitive : primitiveList) {
            primitive.resolve(tempList);
        }
        return tempList;

    }

    public boolean addPrimitive(Primitive p) {
        return primitiveList.add(p);
    }

    public List<Drawable> getDrawables() {
        return drawables;
    }
}
