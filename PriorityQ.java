package org.example;

public class PriorityQ {
    //  Todo: Declare the array to store the queue data
    Node data[];

    //  Todo: Declare the variables to maintain the queue
    int numValidValues;

    //  Todo: Implement the constructor
    public PriorityQ(int maxSize) {
        data = new Node[maxSize];
        numValidValues = 0;
    }
    public int size(){
        return numValidValues;
    }

    //  Todo: insert the passed value in the queue
    public void insert(Node node) {
        if (isFull()) {
            throw new IndexOutOfBoundsException();
        }

        int i;
        for(i=numValidValues-1; i >= 0; i--) {
            if (data[i].getData() > node.getData()) {
                break;
            } else {
                data[i+1] = data[i];
            }
        }

        data[i+1] = node;
        numValidValues++;

    }

    //  Todo: Remove the minimum item
    public Node remove() {
        if (numValidValues == 0) {
            throw new IndexOutOfBoundsException();
        }

        return data[--numValidValues];
    }

    //  Todo: return the minimum item without removing
    public Node peekMin() {
        if (numValidValues == 0) {
            throw new IndexOutOfBoundsException();
        }

        return data[numValidValues - 1];
    }

    // Todo: Implement
    public boolean isEmpty() {
        return numValidValues == 0;
    }

    // Todo: Implement
    public boolean isFull() {
        return numValidValues == data.length;
    }
}
