package app.model.table;

import java.util.*;

public class Table<E extends Collection> {

    private final List<Row<E>> rows;
    private final String typeTitle;

    public Table(String typeTitle) {
        rows = new LinkedList<>();
        this.typeTitle = typeTitle;
    }

    public void printTableHeader() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("New vertices\t|\t");
        stringBuilder.append("Active vertex\t|\t");
        stringBuilder.append("Visited vertex\t|\t");
        stringBuilder.append("Number of visited vertex\t|\t");
        stringBuilder.append(typeTitle);

        System.out.println(stringBuilder);
    }
}
