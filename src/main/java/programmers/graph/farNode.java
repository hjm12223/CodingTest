package programmers.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class farNode {
    public static void main(String[] args) {
        System.out.println(solution(11, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 5}, {3, 6}, {4, 8}, {4, 9}, {5, 9}, {5, 10}, {6, 10}, {6, 11}}) + " (Expected: 4)");
        System.out.println(solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}) + " (Expected: 3)");
        System.out.println(solution(11, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 5}, {3, 6}, {4, 8}, {4, 9}, {5, 9}, {5, 10}, {6, 10}, {6, 11}}) + " (Expected: 4)");
        System.out.println(solution(4, new int[][]{{1, 2}, {2, 3}, {3, 4}}) + " (Expected: 1)");
        System.out.println(solution(2, new int[][]{{1, 2}}) + " (Expected: 1)");
        System.out.println(solution(5, new int[][]{{4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}) + " (Expected: 2)");
        System.out.println(solution(4, new int[][]{{1, 2}, {1, 3}, {2, 3}, {2, 4}, {3, 4}}) + " (Expected: 1)");
        System.out.println(solution(4, new int[][]{{1, 4}, {1, 3}, {2, 3}, {2, 1}}) + " (Expected: 3)");
        System.out.println(solution(4, new int[][]{{3, 4}, {1, 3}, {2, 3}, {2, 1}}) + " (Expected: 1)");
        System.out.println(solution(4, new int[][]{{4, 3}, {1, 3}, {2, 3}}) + " (Expected: 2)");
        System.out.println(solution(5, new int[][]{{4, 3}, {3, 2}, {1, 3},{1,2},{2,4},{5,2}}) + " (Expected: 2)");
        int solution = solution(7, new int[][]{{1, 2}, {1, 3}, {2, 4}, {2, 5}, {3, 6}, {3, 7}});

    }
    static boolean[] isVisited;
    static int[] arr;
    static int[] dist;
    static int max = Integer.MIN_VALUE;

    public static int solution(int n, int[][] edge) {
        arr = new int[n+1];
        dist= new int[n+1];
        isVisited = new boolean[n+1];
        dist[1] = 1;

        Arrays.sort(edge,(i,j) -> {
            if (i[0] == j[0]) return Integer.compare(i[1],j[1]);
            return Integer.compare(i[0],j[0]);

        } );

        for (int i = 0 ; i < edge.length; i++ ){
            bfs(new Node(edge[i][0],edge[i][1]));
        }
        int count = 0;
        Arrays.sort(dist);
        max = dist[dist.length - 1];
        for (int i = dist.length - 1; i > 0; i--) {
            if (max == dist[i]) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    private static void bfs(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        while (!q.isEmpty()){
            Node curr = q.poll();
            if (!isVisited[curr.curr]) {
                if (dist[curr.curr] < dist[curr.next]) {
                    dist[curr.curr] = dist[curr.next] + 1;
                    isVisited[curr.curr] = true;
                } else {
                    dist[curr.next] = dist[curr.curr] + 1;
                    isVisited[curr.next] = true;
                }
            } else if (!isVisited[curr.next]) {
                dist[curr.next] = dist[curr.curr] +1;
                isVisited[curr.next] =true;
            }
        }
    }


    public static class Node{
        int curr;
        int next;

        @Override
        public String toString() {
            return "Node{" +
                    "curr=" + curr +
                    ", next=" + next +
                    '}';
        }

        public Node(int curr, int next) {
            this.curr = curr;
            this.next = next;
        }
    }
    }

/*
1 -> 2 -> 3-> 5
       -> 4
 */