package app.view.forCollections;

import java.io.PrintStream;
import java.util.HashMap;

public final class HashMapPrinter{
    public static void printHashMap(HashMap<Integer, Integer> hashMap) {
        hashMap.forEach((k, v) -> System.out.printf("The new number of vertex %d is %d\n", k + 1, v + 1));
        // FIX vertex 8 index
    }
}
