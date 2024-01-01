package programmers.bruteForce.levelTwo;

import java.util.Arrays;

public class Carpet {
    public static void main(String[] args) {
        int[] solution = solution(10, 2);
        System.out.println("solution = " + Arrays.toString(solution));
    }
    public static int[] solution(int brown, int yellow) {
        int sum = brown + yellow;
        double sqrt = Math.sqrt(sum);
            if (Math.abs(sqrt) == (int) sqrt){
                int abs = (int)Math.abs(sqrt);
                return new int[]{abs, abs};
            }
        int round = (int) Math.floor(sqrt);

            for (int i = 1 ; i < Integer.MAX_VALUE; i++ ) {
                if (i * round == sum) {
                    return new int[]{i, round};
                }
            }
            return new int[]{0,0};
    }
}
