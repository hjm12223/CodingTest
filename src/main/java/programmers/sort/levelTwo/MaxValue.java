package programmers.sort.levelTwo;

import java.util.*;

public class MaxValue {
    public static void main(String[] args) {
        String solution = solution(new int[]{3, 30, 34, 5, 9});
        System.out.println("solution = " + solution);

    }

    public static String solution(int[] numbers) {
        String[] str = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(str, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        StringBuilder stringBuilder = new StringBuilder();
        if (str[0].equals("0")){
            return "0";
        }
        for (String s : str) {
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}
/**
 * 무한반복문을 선언
 *
 * 해시맵으로 우선순위를 준다?
 *
 * number 의 값들을 빼오면서 해당 값이 다른 값들보다 작다 그럼 다른값들
 *
 */

