package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj16938 {
	static int N, L, R, X;
	static boolean[] visited;

	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		visited = new boolean[N];

		Arrays.sort(arr);

		List<List<Integer>> comb = new ArrayList<>();
		dfs(0, comb, new ArrayList<>());

		System.out.println(comb.size());
	}

	private static void dfs(int depth, List<List<Integer>> comb, List<Integer> list) {
		if (list.size() >= 2) {
			if (check(list)) {
				comb.add(new ArrayList<>(list));
			}
		}
		for (int i = depth; i < N; i++) {
			if (!visited[i]) {
				list.add(arr[i]);
				visited[i] = true;
				dfs(i + 1, comb, list);
				visited[i] = false;
				list.remove(list.size() - 1);
			}
		}
	}

	private static boolean check(List<Integer> list) {
		int value = 0;
		for (int i = 0; i < list.size(); i++) {
			value += list.get(i);
		}
		int abs = Math.abs(list.get(0) - list.get(list.size() - 1));
		return value >= L && value <= R && abs >= X;
	}
}
