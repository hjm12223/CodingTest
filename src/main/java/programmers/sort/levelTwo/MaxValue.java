package programmers.sort.levelTwo;

import java.util.*;

public class MaxValue {
    public static void main(String[] args) {
        String solution = solution(new int[]{3, 30, 34, 5, 9});
        System.out.println("solution = " + solution);

    }

    public static String solution(int[] numbers) {
        StringBuilder str = new StringBuilder();
        String[] line = new String[numbers.length];

        for (int i = 0 ; i< numbers.length ; i++){
            line[i] = String.valueOf(numbers[i]);
        }

        Arrays.sort(line, (o1, o2) ->
            (o2 + o1).compareTo(o1 + o2)
        );
        if (line[0].equals("0")){
            return "0";
        }
        for (String s : line) {
            str.append(s);
        }
        return str.toString();
    }

}
