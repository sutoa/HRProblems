package org.nextgen.hackerranksolution.hoffmandecoding;

public class Solution {

    public void decode(String hoffmanCode, Node root){
        System.out.println(decodeHoffmanCode(hoffmanCode, root));
    }

    public String decodeHoffmanCode(String hoffmanCode, Node root) {
        final char[] chars = hoffmanCode.toCharArray();
        StringBuilder clearTxt = new StringBuilder();
        Node currentNode = root;
        for (char c : chars) {
            currentNode = collectCharIfLeafNode(root, clearTxt, currentNode);
            currentNode = navigateToLeaf(c, currentNode);
        }

        collectCharIfLeafNode(root, clearTxt, currentNode);

        return clearTxt.toString();

    }

    private Node collectCharIfLeafNode(Node root, StringBuilder clearTxt, Node startNode) {
        if (leafNode(startNode)) {
            clearTxt.append(startNode.data);
            startNode = root;
        }
        return startNode;
    }

    private Node navigateToLeaf(char c, Node currentNode) {
        if (c == '0')
            return currentNode.left;
        else
            return currentNode.right;

    }

    private boolean leafNode(Node startNode) {
        return startNode.data != '\0';
    }
}

class Node {
    public int frequency;
    public char data;
    public Node left, right;

    public Node(int frequency, char data, Node left, Node right) {
        this.frequency = frequency;
        this.data = data;
        this.left = left;
        this.right = right;
    }
}



