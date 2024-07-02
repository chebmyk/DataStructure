package com.mc.trees;

import java.util.function.Consumer;

public class Tree<T extends Comparable<T>> {
    Node<T> root = null;
    int count = 0;

    public Tree(T value) {
        this.root = new Node<T>(value);
    }

    void insert(T value) {
        Node<T> node = traverse(value);
        if (node.value.compareTo(value) == 0) {
            System.out.println("Warning. Value already exists");
        } else {
            Node<T> newNode = new Node<T>(value);
            newNode.parent = node;
            if (node.value.compareTo(value) < 0 ) {
                node.left = newNode;
            } else {
                node.right = newNode;
            }
            count++;
        }
    }

    Node<T> traverse(T value) {
        Node<T> node = root;
        Node<T> current = root;
        while (node != null) {
            current = node;
            if (node.value.compareTo(value) < 0  ) {
                node = node.left;
            } else if (node.value.compareTo(value) == 0) {
                return node;
            } else {
                node = node.right;
            }
        }
        return current;
    }

    void delete(Node<T> n) {
    }

    void print() {

    }


    void print(Node<T> node) {

    }

    Node<T> search (T value) {
        Node<T> node = traverse(value);
        if (node.value.compareTo(value) == 0) {
            return node;
        } else {
            return null;
        }
    }

    /*
        depthFirstSearch
     */
    void dfsTraverse() {

    }

    /*
        breadthFirstSearch
    */
    void bfsTraverse(Node<T> node, Consumer<T> consumer) {

    }

}
