package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj1301 {
	static Map<String, Long> memo = new HashMap<>();
	static int[] arr;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 구슬의 종류
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		long result = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == 0) continue;
			arr[i]--;
			for (int j = 0; j < N; j++) {
				if (arr[j] == 0) continue;
				arr[j]--;
				result += dfs(i, j, arr);
				arr[j]++;
			}
			arr[i]++;
		}
		System.out.println(result);
	}

	private static long dfs(int i, int j, int[] arr) {
		if (sum(arr)) {
			return 1;
		}
		String key = makeKey(i, j, arr);
		if (memo.containsKey(key))
			return memo.get(key);
		long ways = 0;
		for (int c = 0; c < N; c++) {
			if (arr[c] == 0) continue;
			if (!(i != j && j != c && c != i)) {
				continue;
			}
			arr[c]--;
			ways += dfs(j, c, arr);
			arr[c]++;
		}
		memo.put(key, ways);
		return ways;
	}

	private static String makeKey(int i, int j, int[] arr) {
		StringBuilder sb = new StringBuilder();
		sb.append(i).append(",").append(j).append(",");
		for (int N : arr) {
			sb.append(N).append(",");
		}
		return sb.toString();
	}

	private static boolean sum(int[] arr) {
		int cnt = 0;
		for (int N : arr) {
			cnt += N;
		}
		return cnt == 0;
	}

}
