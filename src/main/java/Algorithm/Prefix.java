package Algorithm;

import java.util.Stack;

public class Prefix {
	public static void main(String[] args) {
		String infix = "A+(B*C-(D/E^F)*G)*H";
		System.out.println("후위 표기식 : " + infixToPostFix(infix));

	}

	public static String infixToPostFix(String infix) {
		StringBuilder postfix = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (char c : infix.toCharArray()) {
			if (Character.isLetterOrDigit(c)) {
				postfix.append(c);
			} else if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					postfix.append(stack.pop());
				}
				stack.pop();
			} else {
				while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek())) {
					postfix.append(stack.pop());
				}
				stack.push(c);
			}
		}
		while (!stack.isEmpty()) {
			postfix.append(stack.pop());
		}
		return postfix.toString();
	}

	private static int precedence(char op) {
		switch (op) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
				return 2;
			case '^':
				return 3;
			default:
				return -1;
		}
	}
}
