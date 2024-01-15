package beakjoon.DfsBfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Boj6593 {
    static int[] dx = new int[]{1, 0, -1, 0, 0, 0};
    static int[] dy = new int[]{0, 1, 0, -1, 0, 0};
    static int[] dz = new int[]{0, 0, 0, 0, 1, -1};
    static char[][][] map;

    static boolean[][][] isVisited;

    static int L;
    static int R;
    static int C;
    static Node s;
    static Node e;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
             L = sc.nextInt();// 빌딩의 층수
             R = sc.nextInt();// 빌딩의 행
             C = sc.nextInt();// 빌딩의 열
            sc.nextLine();

            if (L == 0 && R == 0 && C == 0 ) break;
            map= new char[L][R][C];
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String temp = sc.nextLine();
                    for (int k = 0; k < C; k++) {
                        char c = temp.charAt(k);
                        map[i][j][k] = c;
                        if (map[i][j][k] == 'S') {
                            s = new Node(i,j,k,0);
                        } else if (map[i][j][k] == 'E') {
                            e = new Node(i,j,k,0);
                        }
                    }
                }
                sc.nextLine();
            }
            isVisited = new boolean[L][R][C];
            int result = bfs();
            if (result != -1){
                System.out.println("Escaped in " + result + " minute(s).");
            }else {
                System.out.println("Trapped!");
            }
        }
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(s);
        isVisited[s.l][s.r][s.c] = false;
        while (!q.isEmpty()){
            Node curr = q.poll();
            if (curr.l == e.l && curr.r == e.r && curr.c == e.c) return curr.count;
            for (int k = 0 ; k < 6 ; k++){
                int n_l = curr.l + dx[k];
                int n_r = curr.r + dy[k];
                int n_c = curr.c + dz[k];
                if (n_l<0|| n_l >= L || n_r < 0 || n_r >= R || n_c < 0 || n_c >= C) continue;
                if (!isVisited[n_l][n_r][n_c] && map[n_l][n_r][n_c] != '#'){
                    isVisited[n_l][n_r][n_c] = true;
                    Node next = new Node(n_l, n_r, n_c, curr.count + 1);
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    private static class Node {
        int l;
        int r;
        int c;
        int count ;

        public Node(int l, int r, int c, int count) {
            this.l = l;
            this.r = r;
            this.c = c;
            this.count = count;
        }

    }
}
/**
 * 문제
 * 당신은 상범 빌딩에 갇히고 말았다. 여기서 탈출하는 가장 빠른 길은 무엇일까? 상범 빌딩은 각 변의 길이가 1인 정육면체(단위 정육면체)로 이루어져있다.
 * 각 정육면체는 금으로 이루어져 있어 지나갈 수 없거나, 비어있어서 지나갈 수 있게 되어있다.
 * 당신은 각 칸에서 인접한 6개의 칸(동,서,남,북,상,하)으로 1분의 시간을 들여 이동할 수 있다.
 * 즉, 대각선으로 이동하는 것은 불가능하다.
 * 그리고 상범 빌딩의 바깥면도 모두 금으로 막혀있어 출구를 통해서만 탈출할 수 있다.
 *
 * 당신은 상범 빌딩을 탈출할 수 있을까? 만약 그렇다면 얼마나 걸릴까?
 *
 * 입력
 * 입력은 여러 개의 테스트 케이스로 이루어지며,
 * 각 테스트 케이스는 세 개의 정수 L, R, C로 시작한다.
 * L(1 ≤ L ≤ 30)은 상범 빌딩의 층 수이다. R(1 ≤ R ≤ 30)과 C(1 ≤ C ≤ 30)는 상범 빌딩의 한 층의 행과 열의 개수를 나타낸다.
 *
 * 그 다음 각 줄이 C개의 문자로 이루어진 R개의 행이 L번 주어진다.
 * 각 문자는 상범 빌딩의 한 칸을 나타낸다.
 * 금으로 막혀있어 지나갈 수 없는 칸은 '#'으로 표현되고, 비어있는 칸은 '.'으로 표현된다.
 * 당신의 시작 지점은 'S'로 표현되고, 탈출할 수 있는 출구는 'E'로 표현된다. 각 층 사이에는 빈 줄이 있으며,
 * 입력의 끝은 L, R, C가 모두 0으로 표현된다. 시작 지점과 출구는 항상 하나만 있다.
 *
 * 출력
 * 각 빌딩에 대해 한 줄씩 답을 출력한다. 만약 당신이 탈출할 수 있다면 다음과 같이 출력한다.
 *
 * Escaped in x minute(s).
 * 여기서 x는 상범 빌딩을 탈출하는 데에 필요한 최단 시간이다.
 *
 * 만일 탈출이 불가능하다면, 다음과 같이 출력한다.
 *
 * Trapped!
 * 예제 입력 1
 * 3 4 5
 * S....
 * .###.
 * .##..
 * ###.#
 *
 * #####
 * #####
 * ##.##
 * ##...
 *
 * #####
 * #####
 * #.###
 * ####E
 *
 * 1 3 3
 * S##
 * #E#
 * ###
 *
 * 0 0 0
 * 예제 출력 1
 * Escaped in 11 minute(s).
 * Trapped!
 */