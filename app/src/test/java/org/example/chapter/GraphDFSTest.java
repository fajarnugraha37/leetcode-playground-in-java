package org.example.chapter;

import org.example.data_structure.GraphAdjList;
import org.example.data_structure.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.chapter.GraphDFS.graphDFS;
import static org.junit.jupiter.api.Assertions.*;

class GraphDFSTest {
    @Test
    void main() {
        Vertex[] v = Vertex.valsToVets(new int[]{0, 1, 2, 3, 4, 5, 6});
        Vertex[][] edges = {{v[0], v[1]}, {v[0], v[3]}, {v[1], v[2]},
                {v[2], v[5]}, {v[4], v[5]}, {v[5], v[6]}};
        GraphAdjList graph = new GraphAdjList(edges);
        graph.print();

        List<Vertex> res = graphDFS(graph, v[0]);
        System.out.println("\nDFS");
        System.out.println(Vertex.vetsToVals(res));
    }
}