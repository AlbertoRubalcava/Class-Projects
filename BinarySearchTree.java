package org.example;

import java.sql.PseudoColumnUsage;

public class BinarySearchTree {
    Node root;

    //Todo: Implement Searh for a Node given the key
    public Node find(int key) {
        Node current = root;

        while (current != null) {
            if(key == current.id){
                return current;
            }else if (key < current.id) {
            current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return null; // not in tree
    }

    public void insert(int id, double data) {
        Node newNode = new Node(id, data);
        insert(newNode);
    }

    public void insert(Node newNode) {
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        while (current != null) {
            if (newNode.id == current.id) {
                //No duplicates allowed
                throw new ArrayIndexOutOfBoundsException();
            }

            if (newNode.id < current.id) {
                if (current.leftChild == null) {
                    current.leftChild = newNode;
                    break;
                } else {
                    current = current.leftChild;
                }
            } else {
                if (current.rightChild == null) {
                    current.rightChild = newNode;
                    break;
                } else {
                    current = current.rightChild;
                }
            }
        }
    }


    //Todo: Implement - delete a Node given a key
    public boolean delete(int key) {
        //search for the node to be deleted
        Node current = root;
        Node parent = null;
        boolean isLeftChild = false;

        while (current != null) {
            if (key == current.id) {
                break;
            }
            parent = current;
            if (key < current.id) {
                isLeftChild = true;
                current = current.leftChild;
            } else { //right child
                isLeftChild = false;
                current = current.rightChild;
            }
        }

        if (current == null) {//current will be no if tree is empty or key does not exist
            return false;
        }
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else {
                if (isLeftChild) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                }
            } else if (current.rightChild == null){
            if(current == root) {
                root = current.leftChild;

            } else {
                if (isLeftChild) {
                    parent.leftChild = current.leftChild;
                } else {
                    parent.rightChild = current.leftChild;
                }
            }
        } else if (current.leftChild == null) {
            if(current == root){
                root = current.rightChild;
            } else {
                if(isLeftChild){
                    parent.leftChild = current.rightChild;
                } else {
                    parent.rightChild = parent.leftChild;
                }
            }
        }else {
            Node successor = findSuccessor(current);

            successor.leftChild = current.leftChild;
            if(successor != current.rightChild){
                successor.rightChild = current.rightChild;
            }

            if(current == root) {
                root = successor;
            } else {
                if(isLeftChild) {
                    parent.leftChild = successor;
                } else {
                    parent.rightChild = successor;
                }
            }
        }
        return true;
    }

    private Node findSuccessor (Node delNode){
        Node successor = delNode.rightChild;
        Node successorParent = delNode;

        while(successor.leftChild != null){
            successorParent = successor;
            successor = successor.leftChild;
        }

        if(successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
        }
        return successor;
    }

    private String traversePreOrder(Node root) {
        if (root == null)
            return "";

        StringBuilder sb = new StringBuilder();
        sb.append(root.id);

        String pointerRight = "---";
        String pointerLeft = "|--";

        traverseNodes(sb, "", pointerLeft, root.leftChild, root.rightChild != null);
        traverseNodes(sb, "", pointerRight, root.rightChild, false);

        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer,
                               Node node, boolean hasRightSibling) {

        if (node == null)
            return;

        sb.append("\n");
        sb.append(padding);
        sb.append(pointer);
        sb.append(node.id);

        StringBuilder pb = new StringBuilder(padding);
        if (hasRightSibling)
            pb.append("|  ");
        else
            pb.append("   ");

        String p = pb.toString();
        String pntr = "|--";
        traverseNodes(sb, p, pntr, node.leftChild, node.rightChild != null);
        traverseNodes(sb, p, pntr, node.rightChild, false);
    }

    public Node getMinimum() {
        if (root == null)
            return null;

        Node current = root;
        while(current.leftChild != null)
            current = current.leftChild;

        return current;
    }

    public Node getMaximum() {
        if (root == null)
            return null;

        Node current = root;
        while(current.rightChild != null)
            current = current.rightChild;

        return current;
    }

    public void print() {
        String s = traversePreOrder(root);
        System.out.println(s);
    }
}