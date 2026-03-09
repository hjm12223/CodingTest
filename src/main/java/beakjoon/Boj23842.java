package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj23842 {
	static int[] numbers = new int[] {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
	static int[] arr;
	static boolean isFound = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		dfs(0, N - 4, new StringBuilder());
		if (!isFound) {
			System.out.println("impossible");
		}
	}

	private static void dfs(int depth, int n, StringBuilder sb) {
		if (isFound) return;
		if (depth == 6) {
			if (n == 0)
				check(sb);
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (n - numbers[i] >= 0) {
				n -= numbers[i];
				sb.append(i);
				dfs(depth + 1, n, sb);
				if (isFound) return;
				n += numbers[i];
				sb.deleteCharAt(sb.length() - 1);
			}
		}
	}

	private static void check(StringBuilder sb) {
		int left = (sb.charAt(0) - '0') * 10 + (sb.charAt(1) - '0');
		int right = (sb.charAt(2) - '0') * 10 + (sb.charAt(3) - '0');
		int result = (sb.charAt(4) - '0') * 10 + (sb.charAt(5) - '0');

		if (left + right == result) {
			StringBuilder answer = new StringBuilder();
			answer.append(sb.charAt(0))
				.append(sb.charAt(1))
				.append("+")
				.append(sb.charAt(2))
				.append(sb.charAt(3))
				.append("=")
				.append(sb.charAt(4))
				.append(sb.charAt(5));
			System.out.println(answer);
			isFound = true;
		}
	}
}
