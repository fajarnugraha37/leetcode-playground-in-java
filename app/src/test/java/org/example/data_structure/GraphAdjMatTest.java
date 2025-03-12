package org.example.data_structure;

import org.junit.jupiter.api.Test;


class GraphAdjMatTest {
    @Test
    void main() {
        int[] vertices = { 1, 3, 2, 5, 4 };
        int[][] edges = { { 0, 1 }, { 0, 3 }, { 1, 2 }, { 2, 3 }, { 2, 4 }, { 3, 4 } };
        GraphAdjMat graph = new GraphAdjMat(vertices, edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        graph.addEdge(0, 2);
        System.out.println("\n添加边 1-2 后，图为");
        graph.print();

        graph.removeEdge(0, 1);
        System.out.println("\n删除边 1-3 后，图为");
        graph.print();

        graph.addVertex(6);
        System.out.println("\n添加顶点 6 后，图为");
        graph.print();

        graph.removeVertex(1);
        System.out.println("\n删除顶点 3 后，图为");
        graph.print();
    }
}