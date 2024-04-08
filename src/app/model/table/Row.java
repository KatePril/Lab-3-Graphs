package app.model.table;

import app.enums.VertexStatus;

import java.util.Collection;
import java.util.LinkedList;

public class Row<E extends Collection> {

    private final VertexStatus[] vertexStatuses;
    private final Integer visitedVertex;
    private final Integer numberOfVisitedVertex;
    private final E collection;

    public Row(VertexStatus[] vertexStatuses, E collection, Integer visitedVertex, Integer numberOfVisitedVertex) {
        this.vertexStatuses = vertexStatuses;
        this.visitedVertex = visitedVertex;
        this.numberOfVisitedVertex = numberOfVisitedVertex;
        this.collection = collection;
    }

    public Row(VertexStatus[] vertexStatuses, E collection) {
        this.vertexStatuses = vertexStatuses;
        this.collection = collection;
        this.visitedVertex = null;
        this.numberOfVisitedVertex = null;
    }

    public String getVertexStatusesString() {
        LinkedList<String> list = new LinkedList<>();
        for (int i = 0; i < vertexStatuses.length; i++) {
            if (vertexStatuses[i] == VertexStatus.NEW) {
                list.add(String.valueOf(i+1));
            }
        }
        String output = "-";
        if (!list.isEmpty()){
            output = String.join(", ", list);
        }
        return output;
    }

    public String getActiveVertexString() {
        String output = null;
        for (int i = 0; i < vertexStatuses.length; i++) {
            if (vertexStatuses[i] == VertexStatus.ACTIVE) {
                int tmp = i + 1;
                output = Integer.toString(tmp);
            }
        }
        if (output != null)
            output = "-";
        return output;
    }

    public String getVisitedVertexString() {
        String output = "-";
        if (visitedVertex != null)
            output = visitedVertex.toString();
        return output;
    }

    public String getNumberOfVisitedVertexString() {
        String output = "-";
        if (numberOfVisitedVertex != null)
            output = numberOfVisitedVertex.toString();
        return output;
    }

    public String getCollectionString() {
        String output = collection.toString();
        output = collection.toString().substring(1, output.length() - 1);
        if (output.isEmpty())
            output = "-";
        return output;
    }
}
