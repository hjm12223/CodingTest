package beakjoon.stack;

import java.io.IOException;
import java.util.Stack;

public class Fix {

	public static void main(String[] args) throws IOException {

		String prefi = "+ 3 * 5 2";  // 예시: + 3 * 5 2
		String infi = "3 + 5 * 2";   // 예시: 3 + 5 * 2
		String postfi = "3 5 2 * +"; // 예시: 3 5 2 * +

		int prefix = prefix(prefi.split(" "));
		int infix = infix(infi.split(" "));
		int postfix = postfix(postfi.split(" "));
		System.out.println(prefix);
		System.out.println(infix);
		System.out.println(postfix);

	}

	public static int prefix(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (int i = tokens.length - 1; i >= 0; i--) {
			String token = tokens[i];
			if (isOperator(token)) {
				int operand1 = stack.pop();
				int operand2 = stack.pop();
				int result = performOperation(operand1, token, operand2);
				stack.push(result);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	public static int postfix(String[] tokens) {
		Stack<Integer> stack = new Stack<>();
		for (String token : tokens) {
			if (isOperator(token)) {
				Integer operand1 = stack.pop();
				Integer operand2 = stack.pop();
				stack.push(performOperation(operand1, token, operand2));
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}

	public static int infix(String[] tokens) {

		Stack<Integer> stack = new Stack<>();
		Stack<String> operators = new Stack<>();

		for (String token : tokens) {
			if (isOperator(token)) {
				while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(token)) {
					int operand1 = stack.pop();
					int operand2 = stack.pop();
					int result = performOperation(operand1, token, operand2);
					stack.push(result);
				}
				operators.push(token);
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		while (!operators.isEmpty()) {
			String operator = operators.pop();
			int operand1 = stack.pop();
			int operand2 = stack.pop();
			stack.push(performOperation(operand1, operator, operand2));
		}
		return stack.pop();
	}

	private static int precedence(String operator) {
		if (operator.equals("+") || operator.equals("-")) return 1;
		else return 2;
	}

	private static int performOperation(int operand1, String operator, int operand2) {
		switch (operator) {
			case "+":
				return operand1 + operand2;
			case "-":
				return operand1 - operand2;
			case "*":
				return operand1 * operand2;
			case "/":
				return operand1 / operand2;
		}
		return 0;
	}

	private static boolean isOperator(String token) {
		return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
	}
}
