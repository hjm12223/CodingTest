package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj13908_2 {
	static int N, M;
	static int[] arr;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		if (M != 0) {
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		dfs(new ArrayList<>());
		System.out.println(result);
	}

	private static void dfs(List<Integer> list) {
		if (list.size() == N) {
			if (check(list)) {
				result++;
			}
			return;
		}
		for (int i = 0; i < 10; i++) {
			list.add(i);
			dfs(list);
			list.remove(list.size() - 1);
		}
	}

	private static boolean check(List<Integer> list) {
		int cnt = 0;
		if (M == 0) return true;
		for (int i : arr) {
			if (list.contains(i)) cnt++;
		}
		return cnt == M;
	}
}
