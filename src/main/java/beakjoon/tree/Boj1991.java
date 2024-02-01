package beakjoon.tree;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1991 {
    static Node[] tree;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        tree = new Node[n+1];

        for (int i = 1 ; i <= n ; i++){
            char rootValue = sc.next().charAt(0);
            char leftValue = sc.next().charAt(0);
            char rightValue = sc.next().charAt(0);
            if (tree[rootValue -'A'] == null){
                tree[rootValue - 'A'] = new Node(rootValue);
            }
            if (leftValue != '.'){
                tree[leftValue -'A'] = new Node(leftValue);
                tree[rootValue-'A'].leftValue = tree[leftValue-'A'];
            }
            if (rightValue != '.'){
                tree[rightValue -'A'] = new Node(rightValue);
                tree[rootValue-'A'].rightValue = tree[rightValue-'A'];
            }
        }
        postorder(tree[0]);
        System.out.println();
        inorder(tree[0]);
        System.out.println();
        preorder(tree[0]);
    }

    private static void preorder(Node node) {
        if (node == null) return;
        preorder(node.leftValue);
        preorder(node.rightValue);
        System.out.print(node.value);
    }

    private static void inorder(Node node) {
        if (node == null) return;
        inorder(node.leftValue);
        System.out.print(node.value);
        inorder(node.rightValue);
    }

    private static void postorder(Node node) {
        if (node == null) return;
        System.out.print(node.value);
        postorder(node.leftValue);
        postorder(node.rightValue);
    }

    public static class Node{
        char value;
        Node leftValue;
        Node rightValue;

        public Node(char value) {
            this.value = value;
            this.leftValue = null;
            this.rightValue = null;
        }
    }
}
