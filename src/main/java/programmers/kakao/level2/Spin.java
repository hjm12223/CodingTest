package programmers.kakao.level2;

import javax.lang.model.SourceVersion;
import java.util.Arrays;

public class Spin {
    static int[][] arr;
    public static void main(String[] args) {
        solution(6,6,new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
    }
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer =  new int[queries.length];
        arr = new int[rows+1][columns+1];
        int value = 1;
        for (int i = 1 ; i <= rows; i++){
            for (int j = 1 ; j <= columns; j++){
                arr[i][j] = value;
                value++;
            }
        }
        for (int i = 0; i< queries.length; i++){
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            answer[i] = spin(x1,y1,x2,y2);
        }
        System.out.println("answer = " + Arrays.toString(answer));
        return answer;
    }

    // 2 2 3 4
    // 3 2   4
    // 4 2   4
    // 5 2 3 4
    private static int spin(int x1, int y1, int x2, int y2) {
        int value = arr[x1][y1];
        int min = value;

        // 위쪽 변
        for (int i = x1; i < x2; i++) {
            arr[i][y1] = arr[i + 1][y1];
            min = Math.min(min, arr[i][y1]);
        }

        // 왼쪽 변
        for (int i = y1; i < y2; i++) {
            arr[x2][i] = arr[x2][i + 1];
            min = Math.min(min, arr[x2][i]);
        }

        // 아래쪽 변
        for (int i = x2; i > x1; i--) {
            arr[i][y2] = arr[i - 1][y2];
            min = Math.min(min, arr[i][y2]);
        }

        // 오른쪽 변
        for (int i = y2; i > y1 + 1; i--) {
            arr[x1][i] = arr[x1][i - 1];
            min = Math.min(min, arr[x1][i]);
        }

        arr[x1][y1 + 1] = value;

        return min;
    }
}