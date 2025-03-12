package org.example.data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphAdjList {
    public Map<Vertex, List<Vertex>> adjList;

    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return adjList.size();
    }

    public void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2)
            throw new IllegalArgumentException();
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    public void removeEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2)
            throw new IllegalArgumentException();
        adjList.get(vet1).remove(vet2);
        adjList.get(vet2).remove(vet1);
    }

    public void addVertex(Vertex vet) {
        if (adjList.containsKey(vet))
            return;
        adjList.put(vet, new ArrayList<>());
    }

    public void removeVertex(Vertex vet) {
        if (!adjList.containsKey(vet))
            throw new IllegalArgumentException();
        adjList.remove(vet);
        for (List<Vertex> list : adjList.values()) {
            list.remove(vet);
        }
    }

    public void print() {
        System.out.println("adjList = ");
        for (Map.Entry<Vertex, List<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue())
                tmp.add(vertex.val);
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }
}