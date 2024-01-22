package beakjoon.Dp;

import java.util.Arrays;
import java.util.Scanner;

public class Boj1149 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] color = new int[n][3];
        int[][] dc = new int[n][3];


        for(int i = 0;  i< n ; i++){
            color[i][0] = sc.nextInt();
            color[i][1] = sc.nextInt();
            color[i][2] = sc.nextInt();
        }
        dc[0][0] = color[0][0];
        dc[0][1] = color[0][1];
        dc[0][2] = color[0][2];
        for (int i = 1; i < n ; i++){
            dc[i][0] =  Math.min(dc[i-1][1],dc[i-1][2]) + color[i][0];
            dc[i][1] =  Math.min(dc[i-1][0],dc[i-1][2]) + color[i][1];
            dc[i][2] =  Math.min(dc[i-1][0],dc[i-1][1]) + color[i][2];
        }

        System.out.println(Math.min(Math.min(dc[n-1][0],dc[n-1][1]),dc[n-1][2]));
        /*
        이전의 RGB 와 같은 색인지 확인하는 로직이 필요
         */
    }
}
/**
 * 문제
 * RGB거리에는 집이 N개 있다. 거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
 *
 * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다. 각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때,
 * 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
 *
 * 1번 집의 색은 2번 집의 색과 같지 않아야 한다.
 * N번 집의 색은 N-1번 집의 색과 같지 않아야 한다.
 * i(2 ≤ i ≤ N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다.
 * 입력
 * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다. 둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다. 집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
 */