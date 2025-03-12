package org.example.chapter;

import org.example.data_structure.GraphAdjList;
import org.example.data_structure.Vertex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GraphDFS {
    private GraphDFS() {
    }

    private static void dfs(GraphAdjList graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        res.add(vet);
        visited.add(vet);
        for (Vertex adjVet : graph.adjList.get(vet)) {
            if (visited.contains(adjVet))
                continue;

            dfs(graph, visited, res, adjVet);
        }
    }

    public static List<Vertex> graphDFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        dfs(graph, visited, res, startVet);

        return res;
    }
}
