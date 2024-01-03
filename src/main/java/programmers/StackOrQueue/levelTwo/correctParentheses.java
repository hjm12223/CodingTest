package programmers.StackOrQueue.levelTwo;

import java.util.Stack;

public class correctParentheses {
    public static void main(String[] args) {
        solution("(()(");
    }
    public static boolean solution(String s) {

        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            if (aChar == '('){
                stack.push(')');
            }else if (stack.size() ==0){
                return false;
            }else if (stack.peek() == ')'){
                stack.pop();
            }else {
                return false;
            }
        }
        if (stack.size() != 0){
            return false;
        }
    return true;
    }
}

