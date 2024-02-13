package programmers.kakao.Level3;

import java.security.spec.RSAOtherPrimeInfo;
import java.util.*;

public class SheepAndWolf {
    static List<List<Node>> graph;
    static int[] info;
    public static void main(String[] args) {
//        solution(new int[]{0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
//        solution(new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[][]{{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}});
        solution(new int[]{0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0}, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}, {2, 5}, {2, 6}, {3, 7}, {4, 8}, {6, 9}, {9, 10}});

    }
    public static int solution(int[] info, int[][] edges) {
                List<List<Node>> list = new ArrayList<>();

                for (int i = 0; i <= edges.length; i++) {
                    list.add(new ArrayList<>());
                }
                for (int i = 0; i < edges.length; i++) {
                    int from = edges[i][0];
                    int to = edges[i][1];
                    list.get(from).add(new Node(to, info[to]));
                    list.get(to).add(new Node(from, info[from]));
                }
                Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
                    @Override
                    public int compare(Node o1, Node o2) {
                        return o1.isWolf - o2.isWolf;
                    }
                });
                List<Node> leftList = new ArrayList<>();

                pq.offer(new Node(0, 0));
                int wolf = 0;
                int sheep = 1;
                while (!pq.isEmpty()) {
                    Node curr = pq.poll();
                    for (int i = 0; i < list.get(curr.n).size(); i++) {
                        int nextN = list.get(curr.n).get(i).n;
                        int nextIsWolf = list.get(curr.n).get(i).isWolf;
                        if (list.get(nextN).size() == 0 && nextIsWolf == 1) continue;
                        if (nextIsWolf != 1) {
                            curr.visited =true;
                            pq.offer(new Node(nextN, nextIsWolf));
                            sheep += 1;

                        } else if (sheep > wolf + 1) {
                            curr.visited =true;
                            pq.offer(new Node(nextN, nextIsWolf));
                            wolf += 1;
                        } else {
                            leftList.add(new Node(nextN, nextIsWolf));
                        }

                    }

                }
                for (Node node : leftList) {
                    if (node.visited) continue;
                    pq.offer(node);
                    if (node.isWolf == 1){
                        wolf+=1;
                    }
                }
        System.out.println("sheep = " + sheep);
        System.out.println("wolf = " + wolf);
                while (!pq.isEmpty()) {
                    Node curr = pq.poll();
                    for (int i = 0; i < list.get(curr.n).size(); i++) {

                        int nextN = list.get(curr.n).get(i).n;
                        int nextIsWolf = list.get(curr.n).get(i).isWolf;
                        if (list.get(nextN).size() == 0 && nextIsWolf == 1) continue;

                        if (nextIsWolf != 1 && !curr.visited) {
                            pq.offer(new Node(nextN, nextIsWolf));
                            sheep += 1;
                        } else if (sheep > wolf+1 ) {
                            pq.offer(new Node(nextN, nextIsWolf));
                            wolf += 1;
                        }else {
                            leftList.add(new Node(nextN, nextIsWolf));
                        }
                    }
                }
                return sheep;
            }

            private static class Node {
                @Override
                public String toString() {
                    return "Node{" +
                            "n=" + n +
                            ", isWolf=" + isWolf +
                            '}';
                }

                int n;
                int isWolf;
                int value;
                boolean visited = false;

                public Node(int n, int isWolf) {
                    this.n = n;
                    this.isWolf = isWolf;
                }
            }
        }