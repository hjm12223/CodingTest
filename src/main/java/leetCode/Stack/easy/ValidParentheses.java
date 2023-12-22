package leetCode.Stack.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "){";
        boolean valid = isValid(s);
        System.out.println("s = " + valid);
    }


    public static boolean isValid(String s) {
        if (s.length() == 1){
            return false;
        }
        String[] strs = s.split("");
        Stack<String> stack = new Stack<>();
        for (String str : strs) {
            switch (str) {
                case "[":
                    stack.push("]");
                    break;
                case "{":
                    stack.push("}");
                    break;
                case "(":
                    stack.push(")");
                    break;
                case")" :
                    if (stack.isEmpty()){
                        return false;
                    }
                    String peek = stack.peek();
                    boolean equals = peek.equals(")");
                    if (equals){
                        stack.pop();
                        break;
                    }else {
                        return false;
                    }
                case"}" :
                    if (stack.isEmpty()){
                        return false;
                    }
                    String peek2 = stack.peek();
                    boolean equals2 = peek2.equals("}");
                    if (equals2){
                        stack.pop();
                        break;
                    }else {
                        return false;
                    }
                case"]" :
                    if (stack.isEmpty()){
                        return false;
                    }
                    String peek3 = stack.peek();
                    boolean equals3 = peek3.equals("]");
                    if (equals3){
                        stack.pop();
                        break;
                    }else {
                        return false;
                    }
            }

            }
        return stack.isEmpty();
    }
    }
