package org.example.chapter;

import org.example.data_structure.GraphAdjList;
import org.example.data_structure.Vertex;

import java.util.*;

public final class GraphBFS {
    private GraphBFS() {
    }

    public static List<Vertex> graphBFS(GraphAdjList graph, Vertex startVet) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(startVet);
        Queue<Vertex> que = new LinkedList<>();
        que.offer(startVet);
        while (!que.isEmpty()) {
            Vertex vet = que.poll();
            res.add(vet);
            for (Vertex adjVet : graph.adjList.get(vet)) {
                if (visited.contains(adjVet))
                    continue;
                que.offer(adjVet);
                visited.add(adjVet);
            }
        }

        return res;
    }
}
