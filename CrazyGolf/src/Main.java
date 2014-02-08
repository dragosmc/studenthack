import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Main {
    public static Frame frame;
    public static applet app;

    public static void main(String args[]){

        frame = new Frame("Crazy Golf");
        frame.setSize(1000, 600);
        app = new applet();

        Panel toolbarPanel = new Panel();
        toolbarPanel.setPreferredSize(new Dimension(1000, 30));
        toolbarPanel.setBackground(Color.black);
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        Panel appletPanel = new Panel();
        appletPanel.setPreferredSize(new Dimension(1000, 540));
        appletPanel.setBackground(Color.yellow);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(toolbarPanel);
        frame.add(appletPanel);

        Button drawPoly = new Button("DrawPoly");
        toolbarPanel.add(drawPoly);
        drawPoly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PolyDrawListener polyDrawListener = new PolyDrawListener();
                app.addMouseListener(polyDrawListener);
                app.addMouseMotionListener(polyDrawListener);

            }
        });

        Button drawSquare = new Button("DrawSquare");
        toolbarPanel.add(drawSquare);
        drawSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DropListener dropListener = new DropListener(DropListener.DropType.Rect);
                app.addMouseListener(dropListener);
                app.addMouseMotionListener(dropListener);

            }
        });
        Button drawTriangle = new Button("DrawTriangle");
        toolbarPanel.add(drawTriangle);
        drawTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        })

        ;
        Button setStartPoint = new Button("SetStart");
        toolbarPanel.add(setStartPoint);
        Button setHole = new Button("SetHole");
        toolbarPanel.add(setHole);

        Button setHittingDirection = new Button("Set hitting direction");
        toolbarPanel.add(setHittingDirection);
        setHittingDirection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addMouseListeners(new HitListener());
            }
        });
        appletPanel.add(app);

        //Rect rect = new Rect(new Point(100, 100), new Point(150, 150));

        //app.core.addDrawable(rect);
        app.setPreferredSize(new Dimension(1000, 540));
//        DrawableListener dropListener = new PolyDrawListener();//new DropListener(DropListener.DropType.Rect);
//        app.addMouseListener(dropListener);
//        app.addMouseMotionListener(dropListener);

        frame.setVisible(true);
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }
}
