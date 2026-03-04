package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj12789 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<>();

		int curr = 1;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(st.nextToken());

			if (value != curr) {
				stack.push(value);
			} else {
				curr++;
			}
			while (!stack.isEmpty() && stack.peek() == curr) {
				stack.pop();
				curr++;
			}
		}
		while (!stack.isEmpty()) {
			Integer pop = stack.pop();
			if (pop != curr++) {
				System.out.println("Sad");
				return;
			}
		}
		System.out.println("Nice");
	}
}
