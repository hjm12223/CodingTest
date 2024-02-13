package beakjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import java.util.*;
import java.io.*;

public class Boj4485 {
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static int[][] arr;
    static int[][] dist;
    static boolean[][] visited;

    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 0;
        while (true) {
            cnt++;
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if (n == 0) break;
            arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int parsed = Integer.parseInt(st.nextToken());
                    arr[i][j] = parsed;
                }
            }
            int result = dijkstra();
            System.out.println("Problem " + cnt + ": " + result);
        }
    }

    private static int dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.dist));
        pq.offer(new Node(0, 0, arr[0][0]));

        dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = arr[0][0];

        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.dist > dist[curr.x][curr.y]) continue;

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) continue;

                int newDist = curr.dist + arr[nx][ny];

                if (newDist < dist[nx][ny]) {
                    dist[nx][ny] = newDist;
                    pq.offer(new Node(nx, ny, newDist));
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    static class Node {
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}