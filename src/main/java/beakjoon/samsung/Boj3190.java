package beakjoon.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj3190 {
    static int[] dx = {0, 1, 0, -1}; // 우, 하, 좌, 상 순서
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static int[][] board;
    static Deque<int[]> snake; // 뱀의 위치와 방향을 저장하는 덱
    static int direction; // 뱀의 현재 방향
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        int k = Integer.parseInt(br.readLine());

        // 사과 위치 설정
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1; // 0-based index로 변환
            int col = Integer.parseInt(st.nextToken()) - 1;
            board[row][col] = 1;
        }

        int l = Integer.parseInt(br.readLine());
        Map<Integer, Character> changeDir = new HashMap<>();
        // 방향 변환 정보 저장
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int second = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            changeDir.put(second, dir);
        }

        // 초기 설정
        snake = new LinkedList<>();
        snake.offerLast(new int[]{0, 0}); // 뱀의 머리 위치 (0, 0)
        direction = 0; // 초기 방향은 오른쪽
        time = 0;

        while (true) {
            time++;

            // 다음 위치 계산
            int[] head = snake.peekFirst();
            int nx = head[0] + dx[direction];
            int ny = head[1] + dy[direction];

            // 벽에 부딪히거나 자기 몸에 부딪히는지 확인
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || isBody(nx, ny)) {
                break;
            }

            // 뱀의 머리를 다음 위치로 이동
            snake.offerFirst(new int[]{nx, ny});

            // 이동한 칸에 사과가 있는지 확인
            if (board[nx][ny] == 1) {
                board[nx][ny] = 0; // 사과를 먹었으므로 사과가 없어짐
            } else {
                // 사과가 없으면 꼬리를 줄여서 이동
                snake.pollLast();
            }

            // 방향 전환
            if (changeDir.containsKey(time)) {
                char dir = changeDir.get(time);
                if (dir == 'D') {
                    direction = (direction + 1) % 4; // 오른쪽으로 90도 회전
                } else {
                    direction = (direction + 3) % 4; // 왼쪽으로 90도 회전
                }
            }
        }

        System.out.println(time);
    }

    // 뱀의 몸에 부딪히는지 확인하는 메서드
    static boolean isBody(int x, int y) {
        for (int[] pos : snake) {
            if (pos[0] == x && pos[1] == y) {
                return true;
            }
        }
        return false;
    }
}
/**
 * 'Dummy' 라는 도스게임이 있다.
 * 이 게임에는 뱀이 나와서 기어다니는데,
 * 사과를 먹으면 뱀 길이가 늘어난다.
 * 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
 *
 * 게임은 NxN 정사각 보드위에서 진행되고,
 * 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다.
 * 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다.
 * 뱀은 처음에 오른쪽을 향한다.
 *
 * 뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
 *
 * 먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
 * 만약 벽이나 자기자신의 몸과 부딪히면 게임이 끝난다.
 * 만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
 * 만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
 * 사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
 *
 * 첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
 *
 * 다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
 *
 * 다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
 *
 * 다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데, 정수 X와 문자 C로 이루어져 있으며.
 * 게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다.
 * X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
 */