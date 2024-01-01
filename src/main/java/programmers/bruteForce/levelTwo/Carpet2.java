package programmers.bruteForce.levelTwo;

import java.util.Arrays;

public class Carpet2 {
    public static void main(String[] args) {
        int[] solution = solution(10, 2);
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        double sqrt = Math.sqrt(sum);
        if (sqrt == (int) sqrt){
            return new int[]{(int) sqrt, (int) sqrt};
          }
        for (int column = 1 ; column < sqrt ; column++){
            if (sum % column == 0 ){
                System.out.println("i = " + column);
                int row = sum/column;
                System.out.println("row = " + row);
                    if ((row - 2) * (column - 2) == yellow){
                        return new int[]{row,column};
                    }
                }
            }
        return new int[]{0,0};
    }
}

