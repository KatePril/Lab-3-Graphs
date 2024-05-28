package app.view.graph;

import app.model.graphVisitors.Visitor;
import app.utils.ColorResources;
import app.utils.Constants;
import app.view.View;
import app.view.forCollections.HashMapPrinter;
import app.view.forCollections.MatrixPrinter;

import javax.swing.*;
import java.awt.*;

public class GraphVisitFrame extends DirectedGraphFrame {
    private Visitor visitor;

    public GraphVisitFrame(String title, Integer[][] graphMatrix, Visitor visitor) throws HeadlessException {
        super(title, graphMatrix);
        this.visitor = visitor;
        setGraphMatrix(visitor.getVisitMatrix());
        setVertexStatuses(visitor.getVertices());

        addButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
        setVisible(true);
    }

    private void addButton() {
        JButton jButton = new JButton("Visit");
        jButton.setMaximumSize(new Dimension(150, 50));
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(150, 50));
        panel.add(jButton);
        getContentPane().add(panel);

        jButton.addActionListener(l -> {
            if (visitor.visit()) {
                repaint();
            } else {
                jButton.setText("Visit was completed");
                View<Integer> view = new View<>();
                view.printMatrix("Matrix of the visit", visitor.getVisitMatrix());
                HashMapPrinter.printHashMap(visitor.getNewIndicesOfVertices());
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    protected Color getLineColor() {
        return ColorResources.PURPLE;
    }
}