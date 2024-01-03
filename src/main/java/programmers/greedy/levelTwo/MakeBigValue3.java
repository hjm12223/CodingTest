package programmers.greedy.levelTwo;

import java.util.Stack;

public class MakeBigValue3 {
    public static void main(String[] args) {
        String number = "1924";
        String solution = solution(number, 2);
        System.out.println("solution = " + solution);
    }

    public static String solution(String number, int k) {
        StringBuilder str = new StringBuilder();
        int idx = 0;

        for (int i = 0 ; i < number.length()-k ; i++) {
            char max = 0;
            for (int j = idx;  j<= i+k; j++){
                if (max < number.charAt(j)){
                    max = number.charAt(j);
                    idx = j +1;
                    System.out.println("max = " + max);
                }
            }
            str.append(max);
        }
        return new String(str);
    }
}
