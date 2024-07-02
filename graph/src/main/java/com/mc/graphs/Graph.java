package com.mc.graphs;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Graph<T> {

    private Set<Vertex<T>> nodes = new HashSet<>();
    private Set<Edge<T>> edges = new HashSet<>();

    public Graph() {
    }

    public void add(Vertex<T> newVertex) {
        Objects.requireNonNull(newVertex);
        nodes.add(newVertex);
    }


    public boolean remove(Vertex<T> vertex) {
        Objects.requireNonNull(vertex);
        Set<Edge<T>> children = edges.stream().filter(
                e -> e.source.equals(vertex) || e.target.equals(vertex)
        ).collect(Collectors.toSet());
        return edges.removeAll(children);
    }


    public boolean removeLinkage(Vertex<T> source, Vertex<T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Edge<T> found = findEdge(source, target).orElse(null);
        return found != null && edges.remove(found);
    }


    private Optional<Edge<T>> findEdge(Vertex<T> source, Vertex<T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        return edges.stream().filter(e -> e.target.equals(target) && e.source.equals(source)).findFirst();
    }


    public void addLinkage(Vertex<T> source, Vertex<T> target) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(target);
        Edge<T> found = findEdge(source, target).orElse(null);
        if (found == null) {
            Edge<T> edge = new Edge<>();
            edge.source = source;
            edge.target = target;
            edges.add(edge);
        } else {
            System.out.println("Warning:Graph already contains linkage");
        }
    }


    public List<Vertex<T>> getConnections(Vertex<T> vertex) {
        Objects.requireNonNull(vertex);
        return edges.stream().filter(e -> e.source.equals(vertex))
                .map(e -> e.target)
                .collect(Collectors.toList());
    }

    void print() {
        throw new UnsupportedOperationException();
    }

    public Vertex<T> search (T value) {
        return nodes.stream().filter(n -> n.value.equals(value)).findFirst().orElse(null);
    }

    public List<Vertex<T>> searchAll (T value) {
        return nodes.stream().filter(n -> n.value.equals(value)).collect(Collectors.toList());
    }

    public List<T> path(Vertex<T> from, Vertex<T> to) {
        List<T> result = new LinkedList<>();
        Objects.requireNonNull(from);
        Objects.requireNonNull(to);
        LinkedList<Vertex<T>> trail = new LinkedList<>();

        Vertex<T> r = dfsTraverse(from).peek(v -> trail.add(v))
                .filter(v -> v.value.equals(to.value))
                .findFirst().orElse(null);

        if (r != null) {
            Vertex<T> current =  trail.pollLast();
            result.add(0, current.value);
            while (!trail.isEmpty()) {
                if ( getConnections(trail.getLast()).contains(current)) {
                    result.add(0, trail.getLast().value);
                    current =  trail.getLast();
                }
                trail.pollLast();
            }
        }
        return result;
    }

    /*
        depthFirstSearch
     */
    public Stream<Vertex<T>> dfsTraverse(Vertex<T> vertex) {

        Stream.Builder<Vertex<T>> builder = Stream.builder();
        LinkedList<Vertex<T>> queue = new LinkedList<>();
        HashSet<Vertex<T>> visited = new HashSet<>();
        queue.push(vertex);

        while (!queue.isEmpty()) {
            Vertex<T> current = queue.pop();
            if (!visited.contains(current)) {
                builder.add(current);
                visited.add(current);
                queue.addAll(0, getConnections(current));
            }
        }
        return builder.build();
    }

    /*
        breadthFirstSearch
    */
    public Stream<Vertex<T>> bfsTraverse(Vertex<T> vertex) {

        Stream.Builder<Vertex<T>> builder = Stream.builder();
        LinkedList<Vertex<T>> stack = new LinkedList<>();
        HashSet<Vertex<T>> visited = new HashSet<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            Vertex<T> current = stack.pop();
            if (!visited.contains(current)) {
                builder.add(current);
                visited.add(current);
                stack.addAll(getConnections(current));
            }
        }
        return builder.build();
    }
}
