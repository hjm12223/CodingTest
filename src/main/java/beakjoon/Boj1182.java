package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1182 {
	static int N, M;
	static int[] arr;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		dfs(0, 0, false);
		System.out.println(result);
	}

	private static void dfs(int i, int value, boolean check) {
		if (i == N) {
			if (value == M && check) {
				result++;
			}
			return;
		}
		dfs(i + 1, value + arr[i], true);
		dfs(i + 1, value, check);

	}
}
