package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj17610 {
	static int N;
	static int[] arr;
	static boolean[] visited;

	/**
	 1. 무게추를 오른족에 넣는경우
	 2. 무게추를 왼쪽에 두는경우
	 3. 무게추를 안두는 경우
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int S = Arrays.stream(arr).sum();
		visited = new boolean[S + 1];
		dfs(0, 0);
		int result = 0;
		for (int i = 1; i <= S; i++) {
			if (!visited[i]) result++;
		}
		System.out.println(result);
	}

	private static void dfs(int depth, int sum) {
		if (depth == N) {
			visited[Math.abs(sum)] = true;
			return;
		}
		dfs(depth + 1, sum + arr[depth]);
		dfs(depth + 1, sum - arr[depth]);
		dfs(depth + 1, sum);
	}
}
