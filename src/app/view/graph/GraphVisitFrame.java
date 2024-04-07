package app.view.graph;

import app.entity.Vertex;
import app.enums.Arrow;
import app.enums.VertexStatus;
import app.model.graph.GraphCreator;
import app.model.graphVisitors.Visitor;
import app.utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GraphVisitFrame extends DirectedGraphFrame {
    private Visitor visitor;
    private Integer[][] visitMatrix;

    public GraphVisitFrame(String title, Integer[][] graphMatrix, Visitor visitor) throws HeadlessException {
        super(title, graphMatrix);
        this.visitor = visitor;

        this.visitMatrix = new Integer[graphMatrix.length][graphMatrix[0].length];
        Arrays.fill(visitMatrix, new Integer[graphMatrix[0].length]);
        for (Integer[] row : visitMatrix) {
            Arrays.fill(row, 0);
        }

        setGraphMatrix(visitMatrix);

        JButton jButton = new JButton("visit");
        jButton.setMaximumSize(new Dimension(100, 50));
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(100, 50));
        panel.add(jButton);
        getContentPane().add(panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
