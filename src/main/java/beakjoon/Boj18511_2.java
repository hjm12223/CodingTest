package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18511_2 {
	static int result = 0;
	static int N, maxValue, size;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		maxValue = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		size = String.valueOf(maxValue).length();

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		for (int i = 1; i <= size; i++) {
			dfs(i, new StringBuilder());
		}
		System.out.println(result);

	}

	private static void dfs(int depth, StringBuilder value) {
		if (depth == value.length()) {
			int a = Integer.parseInt(value.toString());
			if (maxValue >= a) {
				result = Math.max(a, result);
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			value.append(arr[i]);
			dfs(depth, value);
			value.deleteCharAt(value.length() - 1);
		}
	}
}
