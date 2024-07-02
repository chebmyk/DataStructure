package com.mc.graphs;

public class GraphApp {
    public static void main(String[] args) {

        Graph<Integer> graph = new Graph<>();

//           / 15 - 10 \
//        5              4
//           \ 20 - 30 /
        Vertex<Integer> n1 = new Vertex<>(5);
        Vertex<Integer> n2 = new Vertex<>(15);
        Vertex<Integer> n3 = new Vertex<>(20);
        Vertex<Integer> n4 = new Vertex<>(10);
        Vertex<Integer> n5 = new Vertex<>(30);
        Vertex<Integer> n6 = new Vertex<>(4);

        graph.add(n1);
        graph.add(n2);
        graph.add(n3);
        graph.add(n4);
        graph.add(n5);
        graph.add(n6);

        graph.addLinkage(n1, n2);
        graph.addLinkage(n1, n3);
        graph.addLinkage(n2, n4);
        graph.addLinkage(n3, n5);

        graph.addLinkage(n4, n6);
        graph.addLinkage(n5, n6);

        System.out.println("=====BFS=======");
        graph.bfsTraverse(n1).forEach(n -> System.out.print("-"+ n.value));
        System.out.println();
        System.out.println("=====DFS=======");
        graph.dfsTraverse(n1).forEach(n -> System.out.print("-"+ n.value));
        System.out.println();
        System.out.println("=====SEARCH=========");
        System.out.println("Found:" + graph.search(15));
        System.out.println();
        System.out.println("=====Path=========");
        System.out.println("Path:" + graph.path(n1, n6));


    }
}
