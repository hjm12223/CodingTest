package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18511 {
	static int N, K, result, size;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;
		arr = new int[K];
		size = String.valueOf(N).length();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= size; i++) {
			dfs(new StringBuilder(), i);
		}
		System.out.println(result);
	}

	private static void dfs(StringBuilder sb, int depth) {
		if (sb.length() == depth) {
			int value = Integer.parseInt(sb.toString());
			if (value <= N)
				result = Math.max(result, value);
			return;
		}
		for (int i = 0; i < K; i++) {
			sb.append(arr[i]);
			dfs(sb, depth);
			sb.deleteCharAt(sb.length() - 1);
		}
	}
}
