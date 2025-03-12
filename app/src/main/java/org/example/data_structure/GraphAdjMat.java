package org.example.data_structure;

import java.util.ArrayList;
import java.util.List;

import org.example.util.PrintUtil;

public class GraphAdjMat {
    List<Integer> vertices;
    List<List<Integer>> adjMat;

    public GraphAdjMat(int[] vertices, int[][] edges) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        for (int val : vertices) {
            addVertex(val);
        }
        for (int[] e : edges) {
            addEdge(e[0], e[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addVertex(int val) {
        int n = size();
        vertices.add(val);
        List<Integer> newRow = new ArrayList<>(n);
        for (int j = 0; j < n; j++) {
            newRow.add(0);
        }
        adjMat.add(newRow);
        for (List<Integer> row : adjMat) {
            row.add(0);
        }
    }

    public void removeVertex(int index) {
        if (index >= size())
            throw new IndexOutOfBoundsException();
        vertices.remove(index);
        adjMat.remove(index);
        for (List<Integer> row : adjMat) {
            row.remove(index);
        }
    }

    public void addEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();
        adjMat.get(i).set(j, 1);
        adjMat.get(j).set(i, 1);
    }

    public void removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j)
            throw new IndexOutOfBoundsException();
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
    }

    public void print() {
        System.out.print("vertices = ");
        System.out.println(vertices);
        System.out.println("metrics = ");
        PrintUtil.printMatrix(adjMat);
    }
}
