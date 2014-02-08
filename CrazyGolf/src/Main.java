import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Adam Bedford on 08/02/14.
 */
public class Main {
    public static Frame frame;
    public static applet app;

    public static void main(String args[]){

        frame = new Frame("Crazy Golf");
        frame.setSize(1000, 600);

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

            }
        });

        Button drawSquare = new Button("DrawSquare");
        toolbarPanel.add(drawSquare);
        Button drawTriangle = new Button("DrawTriangle");
        toolbarPanel.add(drawTriangle);
        Button setStartPoint = new Button("SetStart");
        toolbarPanel.add(setStartPoint);
        Button setHole = new Button("SetHole");
        toolbarPanel.add(setHole);

        app = new applet();
        appletPanel.add(app);

        Rect rect = new Rect(new Point(100, 100), new Point(150, 150));

        app.core.addDrawable(rect);
        app.setPreferredSize(new Dimension(1000, 540));

        frame.setVisible(true);
    }
}
