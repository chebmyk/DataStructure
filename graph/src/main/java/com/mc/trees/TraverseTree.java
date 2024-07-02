package com.mc.trees;

public class TraverseTree {


    public static void main(String[] args) {

        Tree<Integer> tree = new Tree<>(10);

        tree.insert(5);
        tree.insert(17);
        tree.insert(21);
        tree.insert(4);
        tree.insert(1);

        tree.print();

    }
}
