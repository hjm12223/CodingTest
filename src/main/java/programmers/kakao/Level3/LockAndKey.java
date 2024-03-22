package programmers.kakao.Level3;

import java.util.*;

public class LockAndKey {
    public static void main(String[] args) {
        solution(new int[][]{{0,0,0},{1,0,0},{0,1,1}},new int[][]{{1, 1, 1}, {1, 1, 0}, {1, 0, 1}});
    }
    static  Queue<Node> q = new LinkedList<>();
    static   List<Node> list = new ArrayList<>();

    public static boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        int M = key.length; // M은 항상 N 이하이다
        int N = lock.length;

        int[][] arr = new int[N*N][N*N];
        for (int i = 0 ; i < arr.length; i++){
            Arrays.fill(arr[i],-1);
        }


        for (int i = 0; i < lock.length; i++) {
            arr[N/2+N] = lock[i];
            for (int j = 0; j < lock[i].length; j++) {
                arr[N/2+N][j] = lock[i][j];
                if (lock[i][j] != 1) {
                    list.add(new Node(i, j));
                }
            }
        }
        System.out.println("arr = " + Arrays.deepToString(arr));
        bfs(N,M,key);
        return answer;
    }

    private static void bfs(int n, int m, int[][] key) {
        /*
        해당 노드값의 x ,y 를 -n < valne < n+n 만큼에 범위에서 죄측상단부터 우측하단까지 for문을 돌리고 90도를 회전시켜 총 4번을 돌린다
         */
        for (int t = 0; t < 4 ; t++){
            key = rotate(key);
            for (int i = 0; i < key.length; i++) {
                for (int j = 0; j < key[i].length; j++) {
                    if (key[i][j] != 0) {
                        q.offer(new Node(i, j));
                    }
                }
            }
            while (!q.isEmpty()){
                Node currNode = q.poll();

            }
        }
    }
    private static int[][] rotate(int[][] key){
        System.out.println("key = " + Arrays.deepToString(key));
        int[][] tempArr = new int[key[0].length][key.length];
        for (int i = 0 ; i< tempArr.length; i++){
            for (int j = 0 ; j < tempArr[0].length ; j++){
                tempArr[j][tempArr.length-i-1] = key[i][j];
            }
        }
        return tempArr;
    }
    private static class Node{
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
/*
tempArr = [[[0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0, 0, 0]],
            [[0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], [1, 1, 1], [0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0, 0, 0]],
            [[0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0, 0, 0]], [[0, 0, 0], [0, 0, 0], [0, 0, 0]]]

arr = [[-1, -1, -1, -1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, -1, -1, -1, -1, -1], [1, 0, 1, -1, -1, -1,-1, -1, -1], [-1, -1, -1, -1, -1, -1, -1, -1, -1],
        [-1, -1, -1, -1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1, -1, -1, -1], [-1, -1, -1, -1, -1, -1, -1, -1, -1]]

 */
/**
 * 고고학자인 "튜브"는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다.
 * 그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는
 * 방법에 대해 다음과 같이 설명해 주는 종이가 발견되었습니다.
 *
 * 잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의
 * 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.
 *
 * 자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다.
 * 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다.
 * 자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만,
 * 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며 열쇠의 돌기와 자물쇠의 돌기가 만나서는 안됩니다.
 * 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
 *
 * 열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때,
 * 열쇠로 자물쇠를 열수 있으면 true를, 열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.
 *
 * 제한사항
 * key는 M x M(3 ≤ M ≤ 20, M은 자연수)크기 2차원 배열입니다.
 * lock은 N x N(3 ≤ N ≤ 20, N은 자연수)크기 2차원 배열입니다.
 * M은 항상 N 이하입니다.
 * key와 lock의 원소는 0 또는 1로 이루어져 있습니다.
 * 0은 홈 부분, 1은 돌기 부분을 나타냅니다.
 */
/*
tempArr = [[0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0], [1, 1, 1, 1, 1, 1, 1, 1, 1], [0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0], [0, 0, 0, 0, 0, 0, 0, 0, 0]]


 */