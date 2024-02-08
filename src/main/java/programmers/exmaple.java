package programmers;

import java.util.Arrays;

public class exmaple {
    public static void main(String[] args) {
        solution(5);
    }

    public static int[][] solution(int n) {
        int[][] answer = new int[n][n];
        int column = 0;
        int row = 0;
        int cnt = 0;
        int value = 1;

        while (value <= n*n) {
            if (cnt == 0) {  // 오른쪽으로이동
                answer[column][row] = value++;
                row++;

            }
            if (row == n - 1 || answer[column][] > 0) {

                cnt++;
            }
            if (cnt == 1) { // 아래로 이동
                while (column != n-1) {
                    answer[column][row] = value++;
                    column++;
                }
                cnt++;
         }if (cnt == 2){ // 왼쪽으로 이동
                while (row != 0){
                    answer[column][row] = value++;
                    row--;
                }
                cnt++;
            }
            if (cnt == 3){ // 위로이동
                while (answer[column][row] == 0) {
                    answer[column][row] = value++;
                    column--;
                    System.out.println("answer = " + Arrays.deepToString(answer));
                }
                column++;
                cnt = 0;
            }
        }
        /*
        12 - 5 = 7
        11 - 6 = 5
        10 - 7 = 3

        16 - 6 = 10
        17 - 19 = -1
        18




        15 - 7 = 8
        24 - 20 = -4
        25


        14 - 8 = 6
        23 - 21 = 2
        22

        13 - 9 = 4
         */
        return answer;
    }
}
