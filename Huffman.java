package org.example;

import java.util.Scanner;

class Huffman {
    public static void main(String[] args)
    {
        int numValues=0;
        int count=0;

        Scanner sc= new Scanner(System.in);
        System.out.print("Enter a string: ");
        String str= sc.nextLine();

        int[] frequency = new int [256];
        String[] codeTable = new String[256];

        String encodedMessage = "";

        for (int i = 0; i < str.length(); i++)
        {

            frequency[(int) str.charAt(i)]++;
        }

        for (int j : frequency) {
            if (j != 0) {
                numValues++;
            }
        }

        char[] charArray = new char[numValues];
        int[] charFreq = new int[numValues];

        for (int i = 0; i < frequency.length; i++)
        {

            if (frequency[i] != 0)
            {
                charFreq[count] = frequency[i];
                charArray[count] = (char)i;
                count++;
            }
        }

        PriorityQ  q = new PriorityQ(numValues);

        for (int i = 0; i < numValues; i++) {

            Node HuffNode = new Node();

            HuffNode.setC(charArray[i]);
            HuffNode.setData(charFreq[i]);

            HuffNode.setLeft(null);
            HuffNode.setRight(null);

            q.insert(HuffNode);
        }

        Node root = null;

        while (q.size() > 1) {

            Node first = q.peekMin();
            q.remove();

            Node second = q.peekMin();
            q.remove();

            Node f = new Node();

            f.setData(first.getData() + second.getData());
            f.setC('-');

            f.setLeft(first);

            f.setRight(second);

            root = f;

            q.insert(f);
        }

        printCode(root, "", codeTable);
        encodedMessage = encode(codeTable, str, encodedMessage);
        System.out.println("The Encoded Message is: " +encodedMessage);
        String decodedMessage = decode(root, encodedMessage);
        System.out.println("The Decoded Message is: " +decodedMessage);

    }

    public static void printCode(Node root, String s, String[] codeTable)
    {

        if (root.getLeft() == null && root.getRight() == null) { // if true, at leaf node so print

            if(root.getC()!=32) {
                System.out.println(root.getC() + ":" + s);
                codeTable[root.getC()] = s;
            }
            else {
                System.out.println("sp:" + s);
                codeTable[root.getC()] = s;
            }
            return;
        }
        //left 0    right 1
        printCode(root.getLeft(), s + "0",codeTable);
        printCode(root.getRight(), s + "1",codeTable);

    }
    public static String encode(String[] codeTable, String str, String encode) {

            String temp;
        for (int i = 0; i < str.length(); i++) {
            int index = str.charAt(i);
            temp = codeTable[index];
            encode += temp;
        }
        return encode;
    }

    private static String decode(Node root, String str) {
        String decode = "";
        Node current = root;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
            if (current.getLeft() == null && current.getRight() == null) {
                decode += current.getC();
                current = root;
            }
        }
        return decode;
    }

}

