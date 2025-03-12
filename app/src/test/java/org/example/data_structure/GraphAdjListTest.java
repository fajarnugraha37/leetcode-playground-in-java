package org.example.data_structure;

import org.junit.jupiter.api.Test;


class GraphAdjListTest {
    @Test
    void main() {
        Vertex[] v = Vertex.valsToVets(new int[] { 1, 3, 2, 5, 4 });
        Vertex[][] edges = {
                { v[0], v[1] },
                { v[0], v[3] },
                { v[1], v[2] },
                { v[2], v[3] },
                { v[2], v[4] },
                { v[3], v[4] }
        };
        GraphAdjList graph = new GraphAdjList(edges);
        System.out.println("\nGraph = ");
        graph.print();

        graph.addEdge(v[0], v[2]);
        System.out.println("\nadd edge from 1-2");
        graph.print();

        graph.removeEdge(v[0], v[1]);
        System.out.println("\nremove edge from 0 & 1; 1-3");
        graph.print();

        Vertex v5 = new Vertex(6);
        graph.addVertex(v5);
        System.out.println("\nVertex with value 6");
        graph.print();

        graph.removeVertex(v[1]);
        System.out.println("\nremove v[1] 3");
        graph.print();
    }
}