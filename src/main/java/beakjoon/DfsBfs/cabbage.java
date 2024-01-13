package beakjoon.DfsBfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class cabbage {

    static boolean[][] isVisited;
    static int[][] arr;

    static int[] dx = new int[]{1,0,-1,0};
    static int[] dy = new int[]{0,1,0,-1};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        for (int i = 0 ; i  < testCase ; i++) {
            int row = sc.nextInt();
            int column = sc.nextInt();
            arr = new int[row][column];
            isVisited =  new boolean[row][column];
            int cabbageCount = sc.nextInt();
            for (int j = 0 ;j < cabbageCount; j++){
                int i1 = sc.nextInt();
                int i2 = sc.nextInt();
                arr[i1][i2]  = 1;
                q.offer(new int[]{i1,i2});
            }
            int result = bfs(column, row);
            System.out.println(result);
        }
    }

    private static int bfs(int column, int row) {
        int count = 0;
        while (!q.isEmpty()){
            int[] curr = q.poll();
            if (!isVisited[curr[0]][curr[1]]){
                count++;
                isVisited[curr[0]][curr[1]] = true;
            }
            for (int k = 0 ; k < 4 ; k++){
                int n_x = curr[0] + dx[k];
                int n_y = curr[1] + dy[k];
                if (n_x >= 0  &&  n_x < row && n_y >= 0 && n_y < column && !isVisited[n_x][n_y] && arr[n_x][n_y] == 1) {
                    q.offer(new int[]{n_x,n_y});
                    isVisited[n_x][n_y] = true;;
                }
            }
        }
        return count;
    }
}

/**
 * 시간 제한	메모리 제한	제출	정답	맞힌 사람	정답 비율
 * 1 초	512 MB	173805	69935	46819	38.062%
 * 문제
 * 차세대 영농인 한나는 강원도 고랭지에서 유기농 배추를 재배하기로 하였다.
 * 농약을 쓰지 않고 배추를 재배하려면 배추를 해충으로부터 보호하는 것이 중요하기 때문에,
 * 한나는 해충 방지에 효과적인 배추흰지렁이를 구입하기로 결심한다.
 * 이 지렁이는 배추근처에 서식하며 해충을 잡아 먹음으로써 배추를 보호한다.
 * 특히, 어떤 배추에 배추흰지렁이가 한 마리라도 살고 있으면 이 지렁이는 인접한 다른 배추로 이동할 수 있어,
 * 그 배추들 역시 해충으로부터 보호받을 수 있다. 한 배추의 상하좌우 네 방향에 다른 배추가 위치한 경우에 서로 인접해있는 것이다.
 *
 * 한나가 배추를 재배하는 땅은 고르지 못해서 배추를 군데군데 심어 놓았다.
 * 배추들이 모여있는 곳에는 배추흰지렁이가 한 마리만 있으면 되므로 서로 인접해있는 배추들이 몇 군데에 퍼져있는지 조사하면 총 몇 마리의 지렁이가 필요한지 알 수 있다.
 * 예를 들어 배추밭이 아래와 같이 구성되어 있으면 최소 5마리의 배추흰지렁이가 필요하다. 0은 배추가 심어져 있지 않은 땅이고, 1은 배추가 심어져 있는 땅을 나타낸다.
 */
/**
 * 상하 좌우를 확인후 만약 값이 있으면 그값으로 옮김
 * 없을 경우 유기농 배추가 상하좌우에 없다는 뜻이니 count 를 +1 해주고 break를 걸엊무
 */

/**00 01         40
 *    11
 *
 *           33
 *               44
 */