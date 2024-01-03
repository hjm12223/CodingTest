package programmers.greedy.levelTwo;

import java.util.Arrays;
import java.util.Stack;

public class MakeBigValue2 {
    public static void main(String[] args) {
        String number = "1924";
        String solution = solution(number, 2);
        System.out.println("solution = " + solution);
    }

    public static String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack  =new Stack<>(); // 스택선언
        for (int i = 0 ; i < number.length() ; i++){ // 넘버의 배열만큼
            char c = number.charAt(i);// char 꺼내줌
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0){ // 스택이 빌때까지 and 스택의 맨위 값이 C 보다 작을때까지 and K-- 가 1 보다 클때까지
                stack.pop();
            }
            stack.push(c);
        }
        for (int i = 0 ; i <result.length; i++){
            result[i]=stack.get(i);
        }
        return new String(result);
    }
}