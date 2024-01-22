package programmers.dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class OnTheWaySchool {
    static int[][] arr;

    public static void main(String[] args) {
        int solution = solution(4, 3, new int[][]{{2, 2}});
        System.out.println("solution = " + solution);
    }
    public static int solution(int m, int n, int[][] puddles) {
        int mod = 1_000_000_007;
        arr = new int[n+1][m+1];
        for (int[] puddle : puddles) {
            arr[puddle[1]][puddle[0]] = -1;
        }
        arr[1][1] = 1;
        for (int i = 1 ; i < n+1 ; i++) {
            for (int j = 1; j < m + 1; j++) {
                if (arr[i][j] == -1) continue;
                if (arr[i - 1][j] > 0) {
                    arr[i][j] = (arr[i][j] + arr[i - 1][j]);
                }
                if (arr[i][j - 1] > 0) {
                    arr[i][j] = (arr[i][j] + arr[i][j - 1]);
                }
            }
        }
        System.out.println("arr = " + Arrays.deepToString(arr));
        return arr[n][m];
    }
}
