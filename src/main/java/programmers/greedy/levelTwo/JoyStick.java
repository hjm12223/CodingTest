package programmers.greedy.levelTwo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class JoyStick {
    public static void main(String[] args) {
        String name = "JEROEN";
        int solution = solution(name);
        System.out.println("solution = " + solution);
    }
    public static int solution(String name) {
        int answer = -1;

        List<Character> list = new LinkedList<>();
        char[] charArray = name.toCharArray();

        for (char ch = 'A'; ch <= 'Z'; ch++) {
            list.add(ch);
        }

        for (char c : charArray) {
            int leftFind = left(c, list);
            int rightFind = right(c, list);

            if (c != 'A') {
                answer += 1;
                answer += Math.min(rightFind, leftFind);
            }
        }
        return answer;
    }

    public static int left(char c, List<Character> list) {
        int leftFind = 0;
        for (Character character : list) {
            if (c == character) {
                return leftFind;
            }
            leftFind++;
        }
        return leftFind;
    }

    public static int right(char c, List<Character> list) {
        int rightFind = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (c == list.get(i)) {
                return rightFind;
            }
            rightFind++;
        }
        return rightFind;
    }
}