package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.util.*;

public class findArea {
    static int[][] arr;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};

    static int M;
    static int N;
    static boolean[][] isVisited;

    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        int K = sc.nextInt();
        int total = 0;
        arr = new int[M+1][N+1];
        isVisited = new boolean[M+1][N+1];

        for (int i = 0 ; i< K ; i++){
            int firstX = sc.nextInt();
            int firstY = sc.nextInt();
            int lastX = sc.nextInt();
            int lastY = sc.nextInt();

            for (int y = firstY; y < lastY; y++) {
                for (int x = firstX; x < lastX; x++) {
                        arr[y][x] = 1;
                        isVisited[y][x] = true;
                    }

                }
            }
        System.out.println("arr = " + Arrays.deepToString(arr));
            for(int y = 0 ; y < M ; y++){
                for (int x = 0 ; x < N ; x++){
                    if (!isVisited[y][x] && arr[y][x] ==0){
                        bfs(x, y);
                        total++;
                    }
                }
            }
            Collections.sort(list);
        System.out.println("list = " + list);
        }

    private static void bfs(int x, int y) {
        int cnt = 0;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});
        isVisited[y][x] = true;
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int n_x = curr[0] + dx[i];
                int n_y = curr[1] + dy[i];
                if (n_x < 0 || n_x >= N || n_y < 0 || n_y >= M) continue;
                if (!isVisited[n_y][n_x] && arr[n_y][n_x] == 0) {
                    isVisited[n_y][n_x] = true;
                    arr[n_y][n_x] = 1;
                    q.offer(new int[]{n_x, n_y});
                }
            }
        }
        list.add(cnt);
    }
}

/**0 1 0 0 0 0 0
 * 1 1 1 1 0 0 0
 * 1 1 1 1 0 0 0
 * 0 0 0 0 1 1 0
 * 0 0 0 0 1 1 0
 */

/**
 [0, 0, 1, 1, 0, 0, 0]
 [0, 1, 1, 1, 1, 0, 0]
 [0, 0, 1, 1, 0, 0, 0]
 [0, 0, 1, 1, 0, 0, 0]
 [1, 1, 0, 0, 0, 0, 0]
 *//*
        [[true, true, true, true, true, false],
        [true, true, true, true, true, false],
        [false, false, false, false, true, false],
        [false, false, false, false, true, false],
        [true, true, true, true, true, false],
        [true, true, true, true, true, false],
        [true, true, true, true, true, false],
        [false, false, false, false, false, false]


       [[1, 1, 1, 1, 1, 0],
        [1, 1, 1, 1, 1, 0],
        [1, 1, 1, 1, 1, 0],
        [1, 1, 1, 1, 1, 0],
        [1, 1, 1, 1, 1, 0],
        [1, 1, 1, 1, 1, 0]
        [1, 1, 1, 1, 1, 0],
         [0, 0, 0, 0, 0, 0]]
        */