package programmers.kakao.Level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindRoad {
        public static void main(String[] args) {
            int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
            int[][] answer = solution(nodeinfo);
            System.out.println("answer = " + Arrays.deepToString(answer));
        }
    public static int[][] solution(int[][] nodeinfo) {
        List<Node> nodes = new ArrayList<>();
        for (int i = 0 ; i< nodeinfo.length ; i++){
            nodes.add(new Node(i+1,nodeinfo[i][0],nodeinfo[i][1]));
        }
        nodes.sort((o1, o2) -> {
            if (Integer.compare(o2.y, o1.y) == 0) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o2.y, o1.y);
        });
        for (int i = 0 ; i< nodes.size();i++) {
            buildTree(nodes.get(0),nodes.get(i));
        }
        List<Integer> answer1 = new ArrayList<>();
        List<Integer> answer2 = new ArrayList<>();
        Node rootNode = nodes.get(0);
        pre(answer1,rootNode);
        post(answer2,rootNode);
        int[] ans = answer1.stream().mapToInt(Integer::intValue).toArray();
        int[] ans2 = answer2.stream().mapToInt(Integer::intValue).toArray();
        return new int[][]{ans,ans2};
    }

    private static void pre(List<Integer> answer1, Node rootNode) {
            if (rootNode == null) return;
        answer1.add(rootNode.index);
        pre(answer1,rootNode.left);
        pre(answer1,rootNode.right);
    }
    private static void post(List<Integer> answer2, Node rootNode) {
        if (rootNode == null) return;
        post(answer2,rootNode.left);
        post(answer2,rootNode.right);
        answer2.add(rootNode.index);
    }

    private static void buildTree(Node rootNode, Node nextNode) {
            if (rootNode == null || nextNode == null) return;
            if (rootNode.x > nextNode.x){
                if (rootNode.left == null ) {
                    rootNode.left = nextNode;
                }else {
                    buildTree(rootNode.left,nextNode);
                }
            }else if (rootNode.x < nextNode.x){
                if (rootNode.right == null) {
                    rootNode.right = nextNode;
                }else {buildTree(rootNode.right, nextNode);
                }
            }
    }



    private static class Node{
        @Override
        public String toString() {
            return "Node{" +
                    "index=" + index +
                    ", x=" + x +
                    ", y=" + y +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }

        int index, x,y;
            Node left, right;

        public Node(int index, int x, int y) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.left = null;
            this.right = null;
        }
    }
    }
