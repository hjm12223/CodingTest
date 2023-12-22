package leetCode.Stack.Medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {
    private int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<>();
    public MinStack() {
    }

    public void push(int val) {
        if (min >= val){
            stack.push(min);
            System.out.println("stack = " + stack.toString());
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.pop() == min) min = stack.pop();
    }


    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}

/**
 * Runtime
 * 319ms
 * Beats 5.08%of users with Java
 * 런타임이 319ms 로 엄청 오래걸림
 */