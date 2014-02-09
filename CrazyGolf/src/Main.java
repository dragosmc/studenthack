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
    public static CrazyApplet app;
    private static Label bounces = null;

    public static void main(String args[]) {
        frame = new Frame("Crazy Golf");
        frame.setSize(1000, 600);
        frame.getInsets().set(0, 0, 0, 0);
        app = new CrazyApplet();

        Panel toolbarPanel = new Panel();
        toolbarPanel.setPreferredSize(new Dimension(1000, 30));
        toolbarPanel.setBackground(Color.lightGray);
        toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolbarPanel.getInsets().set(0, 0, 0, 0);

        Panel appletPanel = new Panel();
        appletPanel.setPreferredSize(new Dimension(1000, 540));
        appletPanel.getInsets().set(0, 0, 0, 0);

        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        frame.add(toolbarPanel);
        frame.add(appletPanel);

        Button drawPoly = new Button("DrawPoly");
        toolbarPanel.add(drawPoly);
        drawPoly.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                PolyDrawListener polyDrawListener = new PolyDrawListener();
                app.addMouseListeners(polyDrawListener);

            }
        });

        Button drawSquare = new Button("DrawSquare");
        toolbarPanel.add(drawSquare);
        drawSquare.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DropListener dropListener = new DropListener(DropListener.DropType.Rect);
                app.addMouseListeners(dropListener);

            }
        });
        Button drawTriangle = new Button("DrawTriangle");
        toolbarPanel.add(drawTriangle);
        drawTriangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DropListener dropListener = new DropListener(DropListener.DropType.Triangle);
                app.addMouseListeners(dropListener);
            }
        });

        Button setStartPoint = new Button("SetStart");
        toolbarPanel.add(setStartPoint);
        setStartPoint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DropListener dropListener = new DropListener(DropListener.DropType.Ball);
                app.addMouseListeners(dropListener);
            }
        });

        Button setHole = new Button("SetHole");
        toolbarPanel.add(setHole);
        setHole.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DropListener dropListener = new DropListener(DropListener.DropType.Hole);
                app.addMouseListeners(dropListener);
            }
        });

        Button setHittingDirection = new Button("Set Hitting direction");
        toolbarPanel.add(setHittingDirection);
        setHittingDirection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.addMouseListeners(new HitListener());
            }
        });

        Button clear = new Button("Clear");
        toolbarPanel.add(clear);
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.core = new Core();
                addBorder();

            }
        });

        Button changeBounce = new Button("Change Bounce");
        toolbarPanel.add(changeBounce);
        changeBounce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        bounces = new Label("Bounces");
        bounces.setVisible(true);
        bounces.setText("Bounces: 0");
        toolbarPanel.add(bounces);


        app.setPreferredSize(new Dimension(1000, 540));

        addBorder();

        appletPanel.add(app);
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

    private static void addBorder() {
        Poly border = new Poly();
        border.addPoint(new Point(20, 10));
        border.addPoint(new Point(980, 10));
        border.addPoint(new Point(980, 500));
        border.addPoint(new Point(20, 500));

        app.core.addDrawable(border);
    }

    public static Label getBounceCounter() {
        return bounces;
    }
}
