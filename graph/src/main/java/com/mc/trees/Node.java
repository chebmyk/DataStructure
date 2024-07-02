package com.mc.trees;

public class Node<T extends Comparable<T>> {

    Node<T> parent;
    Node<T> left;
    Node<T> right;
    T value;

    public Node(T value) {
        this.value = value;
    }

    public boolean hasChild() {
        return left != null && right != null;
    }
}
