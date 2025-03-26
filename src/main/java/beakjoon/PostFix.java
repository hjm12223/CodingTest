package beakjoon;

import java.util.Stack;

public class PostFix {

	public static void main(String[] args) {
		String infixExpr = "a+b*(c^d-e)^(f+g*h)-i";
		System.out.println("Infix Expression: " + infixExpr);
		System.out.println("Postfix Expression: " + infixToPostfix(infixExpr));
	}

	public static String infixToPostfix(String expression) {
		StringBuilder result = new StringBuilder();
		Stack<Character> stack = new Stack<>();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			// 피연산자는 결과 문자열에 추가
			if (Character.isLetterOrDigit(c)) {
				result.append(c);
			}
			// '('는 스택에 push
			else if (c == '(') {
				stack.push(c);
			}
			// ')'를 만나면 '('까지 pop하여 결과 문자열에 추가
			else if (c == ')') {
				while (!stack.isEmpty() && stack.peek() != '(') {
					result.append(stack.pop());
				}
				stack.pop(); // '(' 제거
			}
			// 연산자인 경우
			else {
				while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
					result.append(stack.pop());
				}
				stack.push(c);
			}
		}

		// 남아 있는 연산자들을 결과 문자열에 추가
		while (!stack.isEmpty()) {
			result.append(stack.pop());
		}

		return result.toString();
	}

	// 연산자 우선순위를 반환하는 메서드
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
