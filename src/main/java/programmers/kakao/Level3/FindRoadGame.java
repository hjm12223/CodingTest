package programmers.kakao.Level3;

import java.util.*;
import java.util.stream.Collectors;

public class FindRoadGame {
    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        int[][] answer = solution(nodeinfo);
        System.out.println("answer = " + Arrays.deepToString(answer));
    }

    public static int[][] solution(int[][] nodeinfo) {

        List<Node> nodes = new ArrayList<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodes.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1));
        }
        Collections.sort(nodes,(o1, o2) -> {
            if (Integer.compare(o1.y,o2.y) == 0){
                return Integer.compare(o1.x,o2.x);
            }else {
                return Integer.compare(o2.y,o1.y);
            }
        });
        buildTree(nodes);
        List<Integer> answer1 = new ArrayList<>();
        List<Integer> answer2 = new ArrayList<>();
        Node rootNode = nodes.get(0);
        preOrder(answer1,rootNode);
        postOrder(answer2,rootNode);
        return new int[][]{answer1.stream().mapToInt(Integer::new).toArray(),answer2.stream().mapToInt(Integer::new).toArray()};
    }

    private static void postOrder(List<Integer> answer2, Node rootNode) {
        if (rootNode == null) return;
        postOrder(answer2,rootNode.left);
        postOrder(answer2,rootNode.right);
        answer2.add(rootNode.index);
    }

    private static void preOrder(List<Integer> answer1, Node rootNode) {
        if (rootNode == null) return;
        answer1.add(rootNode.index);
        preOrder(answer1,rootNode.left);
        preOrder(answer1,rootNode.right);
    }

    private static Node buildTree(List<Node> nodes) {
        if (nodes.isEmpty()) return null;

        Node root = nodes.get(0);
        for (int i = 1; i < nodes.size(); i++) {
            insertNode(root, nodes.get(i));
        }
        return root;
    }

    private static void insertNode(Node root, Node nextNode) {
        if (nextNode.x < root.x) {
            if (root.left == null) {
                root.left = nextNode;
            } else {
                insertNode(root.left, nextNode);
            }
        } else {
            if (root.right == null) {
                root.right = nextNode;
            } else {
                insertNode(root.right, nextNode);
            }
        }
    }
    private static class Node {
        int x, y, index;
        Node left, right;


        public Node(int x, int y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }
}