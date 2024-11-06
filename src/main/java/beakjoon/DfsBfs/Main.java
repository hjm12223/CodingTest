package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();

		Stack<Character> stack = new Stack<>();
		int result = 0;
		int temp = 1;

		for (int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);

			if (ch == '(') {
				stack.push(ch);
				temp *= 2;
			} else if (ch == '[') {
				stack.push(ch);
				temp *= 3;
			} else if (ch == ')') {
				if (stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					break;
				}
				if (input.charAt(i - 1) == '(') {
					result += temp;
				}
				stack.pop();
				temp /= 2;
			} else if (ch == ']') {
				if (stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					break;
				}
				if (input.charAt(i - 1) == '[') {
					result += temp;
				}
				stack.pop();
				temp /= 3;
			}
		}

		if (!stack.isEmpty()) {
			result = 0;
		}

		System.out.println(result);
	}
}
