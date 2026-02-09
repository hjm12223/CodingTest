package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj1874 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();
		int cur = 1;
		for (int i = 0; i < N; i++) {
			int target = Integer.parseInt(br.readLine());

			while (cur <= target) {
				stack.push(cur++);
				sb.append("+\n");
			}
			if (stack.isEmpty() || stack.peek() != target) {
				System.out.println("No");
				return;
			}
			stack.pop();
			sb.append("-\n");
		}
		System.out.println(sb);
	}
}
