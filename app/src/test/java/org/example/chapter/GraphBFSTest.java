package org.example.chapter;

import org.example.data_structure.GraphAdjList;
import org.example.data_structure.Vertex;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.example.chapter.GraphBFS.graphBFS;
import static org.junit.jupiter.api.Assertions.*;

class GraphBFSTest {
    @Test
    void main() {
        Vertex[] v = Vertex.valsToVets(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        Vertex[][] edges = {
                {v[0], v[1]},
                {v[0], v[3]},
                {v[1], v[2]},
                {v[1], v[4]},
                {v[2], v[5]},
                {v[3], v[4]},
                {v[3], v[6]},
                {v[4], v[5]},
                {v[4], v[7]},
                {v[5], v[8]},
                {v[6], v[7]},
                {v[7], v[8]}
        };
        GraphAdjList graph = new GraphAdjList(edges);
        graph.print();

        List<Vertex> res = graphBFS(graph, v[0]);
        System.out.println("\nBFS");
        System.out.println(Vertex.vetsToVals(res));
    }
}