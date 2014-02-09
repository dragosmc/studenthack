import java.awt.*;
import java.nio.FloatBuffer;
import java.util.LinkedList;
import java.util.List;

import collisionable_src.Segment;
import drawable_src.Rect;
import interface_src.Collisionable;
import interface_src.Drawable;
import interface_src.Primitive;
import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class OpenGLDisplay {
    public List<Drawable> drawableList;
    private double angle;

    public OpenGLDisplay(List<Drawable> drawables) {
        if (drawables == null) {
            drawableList = new LinkedList<Drawable>();

            drawableList.add(new Rect(new Point(0, 0), new Point(50, 50)));
            drawableList.add(new Rect(new Point(0, 0), new Point(25, 25)));
        } else {
            drawableList = drawables;
        }

        try {
            Display.setDisplayMode(new DisplayMode(720, 400));
            Display.create();

            FloatBuffer position = BufferUtils.createFloatBuffer(4);
            position.put(new float[]{400f, 400f, 800.0f, 1.0f}).flip();
            FloatBuffer diffuse = BufferUtils.createFloatBuffer(4);
            diffuse.put(new float[]{0.8f, 0.8f, 0.8f, 1f}).flip();
            FloatBuffer ambient = BufferUtils.createFloatBuffer(4);
            ambient.put(new float[]{0.5f, 0.5f, 0.5f, 1f}).flip();

            GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, position);
            GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, diffuse);

            GL11.glEnable(GL11.GL_LIGHT0);
            GL11.glLightModel(GL_AMBIENT, ambient);
            GL11.glLightModeli(GL_LIGHT_MODEL_TWO_SIDE, GL_TRUE);
            GL11.glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);

            GL11.glEnable(GL_COLOR_MATERIAL);
            GL11.glShadeModel(GL_FLAT);

            GL11.glEnable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_LIGHTING);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            //GL11.glFrustum(-1, 1, .55, .55, 1, 1000);
            GLU.gluPerspective(45, (float) (720.0 / 400.0), 1, 1000);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            angle = 0;
            while (!Display.isCloseRequested()) {
                update();
                angle++;
            }

            Display.destroy();
        } catch (LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void resolve(List<Segment> segs) {
        for (Drawable drawable : drawableList) {
            if (drawable instanceof Primitive) {
                List<Collisionable> collisions = new LinkedList<Collisionable>();

                Primitive primitive = (Primitive) drawable;
                primitive.resolve(collisions);

                for (Collisionable collision : collisions) {
                    if (collision instanceof Segment)
                        segs.add((Segment) collision);
                }
            }
        }
    }

    static float bgcolor = 0.5f;

    private void update() {
        GL11.glLoadIdentity();
        GL11.glViewport(0, 0, 720, 400);

        GL11.glClear(GL11.GL_DEPTH_BUFFER_BIT | GL11.GL_COLOR_BUFFER_BIT);

        GL11.glClearColor(1, 1, 1, 1);

        GL11.glColor3d(1, 1, 1);

        List<Segment> segments = new LinkedList<Segment>();
        resolve(segments);

        GL11.glPushMatrix();
        {
            GL11.glTranslated(0, 0, -500);

            GL11.glPushMatrix();
            {
                GL11.glRotated(30, 1, 0, 0);
                GL11.glRotated(angle, 0, 1, 0);

                GL11.glBegin(GL_QUADS);
                for (Segment segment : segments) {
                    GL11.glVertex3d(segment.getX(), 0, segment.getY());
                    GL11.glVertex3d(segment.getX() + segment.getDx(), 0, segment.getY() + segment.getDy());
                    GL11.glVertex3d(segment.getX() + segment.getDx(), 20, segment.getY() + segment.getDy());
                    GL11.glVertex3d(segment.getX(), 20, segment.getY());

                    double l = Math.sqrt(Math.pow(segment.getDy(), 2) + Math.pow(segment.getDx(), 2));
                    GL11.glNormal3d(-segment.getDy() / l, 0, segment.getDx() / l);
                }
                GL11.glEnd();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();

        //GL11.glMatrixMode(GL11.GL_PROJECTION);
        Display.update();
        Display.sync(25);
    }

    public static void main(String args[]) {
        new OpenGLDisplay(null);
    }
}
