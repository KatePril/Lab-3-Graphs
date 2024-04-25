package app.view.graph;

import app.enums.Arrow;
import app.model.PrimeAlgorithm;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static java.lang.String.format;

public class DirectedWeightedGraph extends WeightedGraphFrame {

    private final PrimeAlgorithm primeAlgorithm;
    public DirectedWeightedGraph(String title, Integer[][] graphMatrix, Integer[][] weightMatrix) throws HeadlessException {
        super(title, graphMatrix, weightMatrix);
        this.primeAlgorithm = new PrimeAlgorithm(graphMatrix, weightMatrix);
        addButton();
    }

    private void addButton() {
        JButton jButton = new JButton("Build tree");
        jButton.setMaximumSize(new Dimension(200, 50));
        JPanel panel = new JPanel();
        panel.setMaximumSize(new Dimension(200, 50));
        panel.add(jButton);
        getContentPane().add(panel);

        jButton.addActionListener(l -> {
            if (primeAlgorithm.buildTree()) {
                repaint();
            } else {
                jButton.setText(format("Total weight: %d", primeAlgorithm.getTotalWeight()));
            }
        });
    }

    @Override
    protected void paintLines(Graphics g) {
        ArrayList<ArrayList<Integer>> edges = primeAlgorithm.getIncludedEdges();
        for (ArrayList<Integer> edge : edges) {
            linePainter.paintLine(g, vertices[edge.get(0)], vertices[edge.get(1)], weightMatrix[edge.get(0)][edge.get(1)], Arrow.VERTEX_TWO);
        }
    }
}
