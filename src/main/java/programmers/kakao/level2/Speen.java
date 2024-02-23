package programmers.kakao.level2;

import java.util.Arrays;

public class Speen {
    static int[][] arr;
    public static void main(String[] args) {
        solution(6,6,new int[][]{{2,2,5,4},{3,3,6,6},{5,1,6,3}});
    }
    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = {};
        int[][] arr = new int[rows+1][columns+1];
        int value = 1;
        for (int i = 1 ; i <= rows; i++){
            for (int j = 1 ; j <= columns; j++){
                arr[i][j] = value;
                value++;
            }
        }
        System.out.println("arr = " + Arrays.deepToString(arr));
        for (int i = 0; i< queries.length; i++){
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            spin(x1,y1,x2,y2);
        }
        return answer;
    }

    // 2 2 3 4
    // 3 2   4
    // 4 2   4
    // 5 2 3 4
    private static void spin(int x1, int y1, int x2, int y2) {
        int temp = 0;
        for (int i = x1; i <= x2 ; i++){
            int tempValue = arr[i-1][y1];
            for (int j = y1 ; j <= y2 ; j++){
                if (i != x1 || i != x2){
                 if (j != y1 || j != y2) continue;
                 arr[i][j] = tempValue;
                }else {
                }
            }
        }
        System.out.println("arr = " + Arrays.deepToString(arr));
    }
}
