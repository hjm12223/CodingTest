package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj7490 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			for (int i = 1; i <= N; i++)
				arr[i - 1] = i;
			dfs(arr, "1", 1);
			System.out.println();
		}
	}

	static void dfs(int[] arr, String expr, int depth) {
		if (depth == arr.length) {
			if (cal(expr) == 0) {
				System.out.println(expr);
			}
			return;
		}
		int next = depth + 1;
		dfs(arr, expr + " " + next, next);
		dfs(arr, expr + "+" + next, next);
		dfs(arr, expr + "-" + next, next);
	}

	private static int cal(String expr) {
		String str = expr.replace(" ", "");
		int num = 0;
		int sum = 0;
		char m = '+';
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (Character.isDigit(c)) {
				num = num * 10 + (c - '0');
			}
			if (!Character.isDigit(c) || i == str.length() - 1) {
				if (m == '+') sum += num;
				else sum -= num;
				m = c;
				num = 0;
			}
		}
		return sum;
	}
}