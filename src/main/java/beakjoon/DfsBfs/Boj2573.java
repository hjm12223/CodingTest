package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2573 {
    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy=  new int[]{0,1,0,-1};
    static  int[][] arr;
    static int row;
    static int column;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());

        arr = new int[row][column];

        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            if (checkIceberg()) { // 분리되었는지 확인
                System.out.println(year);
                break;
            }
            if (meltIceberg()) { // 빙산이 모두 녹았는지 확인
                System.out.println(0);
                break;
            }
            year++;
        }
    }

    static boolean checkIceberg() {
        int cnt = 0;
        visited = new boolean[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] > 0 && !visited[i][j]) {
                    bfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt >= 2; // 빙산이 분리되었는지 여부 반환
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx >= 0 && nx < row && ny >= 0 && ny < column && arr[nx][ny] > 0 && !visited[nx][ny]) {
                    queue.offer(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static boolean meltIceberg() {
        int[][] next = new int[row][column];
        boolean allMelted = true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (arr[i][j] > 0) {
                    allMelted = false; // 빙산이 하나라도 남아있으면 false로 변경
                    int seaCnt = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (nx >= 0 && nx < row && ny >= 0 && ny < column && arr[nx][ny] == 0) {
                            seaCnt++;
                        }
                    }
                    next[i][j] = Math.max(arr[i][j] - seaCnt, 0);
                }
            }
        }

        if (allMelted) return true; // 빙산이 모두 녹았는지 여부 반환

        arr = next; // 빙산의 높이를 갱신
        return false;
    }
}
/*
curr = [
[0, 0, 0, 0, 0, 0, 0],
[0, 0, 2, 3, 1, 0, 0],
[0, 0, 0, 2, 5, 0, 0],
[0, 4, 3, 0, 1, 0, 0],
[0, 0, 0, 0, 0, 0, 0]]
 */