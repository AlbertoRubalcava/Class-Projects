package org.example;

public class TestBinarySearchTree {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(50, 1.5);
        tree.insert(25, 1.7);
        tree.insert(15, 1.9);
        tree.insert(75, 298.5);
        tree.insert(18, 1.9);
        tree.insert(35, 1.9);
        tree.insert(45, 1.9);
        tree.insert(65, 1.9);
        tree.insert(30, 1.9);
        tree.insert(5, 1.9);

        tree.print();
        tree.delete(25);
        tree.print();

        Node min = tree.getMaximum();
        if (min != null) {
            System.out.print("Minimum: ");
            min.display();
        }
    }
}