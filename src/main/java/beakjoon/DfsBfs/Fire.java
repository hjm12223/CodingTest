package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;

public class Fire {
    static int n, m;
    static char[][] board;
    static int[][] dist1; // 불의 전파 시간
    static int[][] dist2; // 지훈이의 이동 시간
    static int[] dx, dy;
    static Queue<Pair> Q1;
    static Queue<Pair> Q2;

    public static void main(String[] args) throws IOException {


            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            board = new char[n][m];
            dist1 = new int[n][m]; // 불의 전파 시간
            dist2 = new int[n][m]; // 지훈이의 이동 시간
            Q1 = new LinkedList<>();
            Q2 = new LinkedList<>();
            dx = new int[]{1, 0, -1, 0};
            dy = new int[]{0, 1, 0, -1};

            // 배열 초기화
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    board[i][j] = s.charAt(j);
                    dist1[i][j] = -1;
                    dist2[i][j] = -1;
                    if (board[i][j] == 'F') {
                        Q1.offer(new Pair(i, j));
                        dist1[i][j] = 0;
                    }
                    if (board[i][j] == 'J') {
                        Q2.offer(new Pair(i, j));
                        dist2[i][j] = 0;
                    }
                }
            }
            // 불에 대한 BFS
            while (!Q1.isEmpty()) {
                Pair cur = Q1.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.X + dx[dir];
                    int ny = cur.Y + dy[dir];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                    if (dist1[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                    dist1[nx][ny] = dist1[cur.X][cur.Y] + 1;
                    Q1.offer(new Pair(nx, ny));
                }
            }

            // 지훈이에 대한 BFS
            while (!Q2.isEmpty()) {
                Pair cur = Q2.poll();
                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.X + dx[dir];
                    int ny = cur.Y + dy[dir];
                    // 범위를 벗어났다는 것은 탈출에 성공했다는 의미
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        System.out.println(dist2[cur.X][cur.Y] + 1);
                        return;
                    }
                    if (dist2[nx][ny] >= 0 || board[nx][ny] == '#') continue;
                    // 불의 전파 시간을 조건에 추가
                    if (dist1[nx][ny] <= dist2[cur.X][cur.Y] + 1) continue;
                    dist2[nx][ny] = dist2[cur.X][cur.Y] + 1;
                    Q2.offer(new Pair(nx, ny));
                }
            }

            System.out.println("IMPOSSIBLE");
        }
    public static class Pair{
        int X;
        int Y;

        public Pair(int x, int y) {
            X = x;
            Y = y;
        }
    }
    }