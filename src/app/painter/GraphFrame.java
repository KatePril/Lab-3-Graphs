package app.painter;

import app.model.Node;

import javax.swing.*;
import java.awt.*;

public class GraphFrame extends JFrame {

    private LinePainter linePainter = new LinePainter();
    private NodePainter nodePainter = new NodePainter();

    public GraphFrame(String title) throws HeadlessException {
        super(title);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        nodePainter.paintNode(g, new Node(1, 100, 100));
//        nodePainter.paintNode(g);
//        linePainter.paintLine(g);

    }
}
