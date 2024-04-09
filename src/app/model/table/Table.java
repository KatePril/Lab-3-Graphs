package app.model.table;

import app.utils.Constants;

import java.util.*;

import static java.lang.String.format;

public class Table<E extends Collection> {

    private final LinkedList<Row<E>> rows;
    private final String typeTitle;

    public Table(String typeTitle) {
        rows = new LinkedList<>();
        this.typeTitle = typeTitle;
    }

    public void printRow(Row<E> row, int index) {
        System.out.printf("Step %d:\n", index);
        System.out.println("New vertices: " + row.getNewVerticesString());
        System.out.printf("Active vertex: %s\n", row.getActiveVertexString());
        System.out.printf("Visited vertex: %s\n", row.getVisitedVertexString());
        System.out.printf("Number of visited vertex: %s\n", row.getNumberOfVisitedVertexString());
        System.out.println(typeTitle + ": " + row.getCollectionString());
        System.out.println("----------------------------------------------------");
    }

    public void addRow(Row<E> row) {
        rows.add(row);
//        printRow(rows.getLast(), rows.size());
    }
}
