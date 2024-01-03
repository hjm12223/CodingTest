package programmers.StackOrQueue.levelOne;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class HateSameNumber {
    public static void main(String[] args) {
        int[] arr = new int[]{1,1,3,3,0,1,1};
        int[] solution = solution(arr);
        System.out.println("solution = " + Arrays.toString(solution));
    }
        public static int[] solution(int []arr) {
            Stack<Integer> stack = new Stack<>();
            for (int i : arr) {
                if (stack.size() == 0){
                    stack.push(i);
                } else if (stack.peek() != i) {
                    stack.push(i);
                }
            }
            // 스트림
            return stack.stream().mapToInt(i -> i).toArray();
        }
    }
