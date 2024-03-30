package app.model;

import app.model.graphInformators.DirectedGraphInformator;
import app.model.graphInformators.UndirectedGraphInformator;
import app.model.matrix.dataSupliers.DirectedGraphMatrixCreator;
import app.model.matrix.dataSupliers.KGenerator;
import app.model.matrix.dataSupliers.RandomMatrixCreator;
import app.model.matrix.dataSupliers.UndirectedGraphMatrixCreator;
import app.utils.Constants;

import java.util.ArrayList;

public class Model {
    private final int n;
    private final UndirectedGraphInformator undirectedGraph;
    private final DirectedGraphInformator directedGraph;
    private final DirectedGraphInformator directedGraphTwo;
    private final DirectedGraphInformator componentsGraph;

    public Model() {
        this.n = calculateVerticesNumber();

        Integer[][] directedGraphMatrix = createDirectedGraphMatrix(new RandomMatrixCreator(this.n, KGenerator.getK1()).getMatrix());
        Integer[][] undirectedGraphMatrix = createUndirectedGraphMatrix(directedGraphMatrix);

        undirectedGraph = new UndirectedGraphInformator(undirectedGraphMatrix, false);
        directedGraph = new DirectedGraphInformator(directedGraphMatrix, true);

        Integer[][] directedGraphMatrixTwo = createDirectedGraphMatrix(new RandomMatrixCreator(this.n, KGenerator.getK2()).getMatrix());
        directedGraphTwo = new DirectedGraphInformator(directedGraphMatrixTwo, true);

        Integer[][] componentMatrix = createMatrixOfConnectionsGraph(directedGraphTwo.getComponents(), directedGraphTwo.getAdjacencyMatrix());
        componentsGraph = new DirectedGraphInformator(componentMatrix, true);
    }

    private Integer[][] createDirectedGraphMatrix(Double[][] matrix) {
        DirectedGraphMatrixCreator directedGraphMatrixCreator = new DirectedGraphMatrixCreator(matrix);
        return directedGraphMatrixCreator.getGraphMatrix();
    }

    private Integer[][] createUndirectedGraphMatrix(Integer[][] directedGraphMatrix) {
        UndirectedGraphMatrixCreator undirectedGraphMatrixCreator = new UndirectedGraphMatrixCreator(directedGraphMatrix);
        return undirectedGraphMatrixCreator.getGraphMatrix();
    }

    public Integer[][] createMatrixOfConnectionsGraph(ArrayList<ArrayList<Integer>> components, Integer[][] adjacencyMatrix) {
        Integer[][] componentGraph = new Integer[components.size()][components.size()];
        for (int i = 0; i < componentGraph.length; i++) {
            for (int j = 0; j < componentGraph.length; j++) {
                if (i == j) {
                    componentGraph[i][j] = 0;
                } else {
                    ArrayList<Integer> k1 = components.get(i);
                    ArrayList<Integer> k2 = components.get(j);
                    boolean flag = false;
                    for (int k = 0; k < k1.size(); k++) {
                        for (int l = 0; l < k2.size(); l++) {
                            if (adjacencyMatrix[k1.get(k)-1][k2.get(l)-1] == 1) {
                                componentGraph[i][j] = 1;
                                flag = true;
                                break;
                            } else {
                                componentGraph[i][j] = 0;
                            }
                        }
                        if (flag)
                            break;
                    }
                }
            }
        }
        return componentGraph;
    }



    private int calculateVerticesNumber() {
        return 10 + Constants.n3;
    }

    public int getN() {
        return n;
    }


    public UndirectedGraphInformator getUndirectedGraph() {
        return undirectedGraph;
    }

    public DirectedGraphInformator getDirectedGraph() {
        return directedGraph;
    }

    public DirectedGraphInformator getDirectedGraphTwo() {
        return directedGraphTwo;
    }

    public DirectedGraphInformator getComponentsGraph() {
        return componentsGraph;
    }
}
