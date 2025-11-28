package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15661 {
	static int[][] arr;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		List<List<Integer>> comb = new ArrayList<>();
		dfs(comb, new ArrayList<>(), 0);
		int result = Integer.MAX_VALUE;
		for (List<Integer> startMember : comb) {
			List<Integer> linkMember = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				if (!startMember.contains(i)) {
					linkMember.add(i);
				}
			}
			result = Math.min(result, Math.abs(calc(startMember) - calc(linkMember)));
		}
		System.out.println(result);
	}

	private static int calc(List<Integer> team) {
		int sum = 0;
		for (int i = 0; i < team.size(); i++) {
			for (int j = i + 1; j < team.size(); j++) {
				int x = team.get(i);
				int y = team.get(j);
				sum += arr[x][y] + arr[y][x];
			}
		}
		return sum;
	}

	private static void dfs(List<List<Integer>> comb, List<Integer> numbers, int depth) {
		if (!numbers.isEmpty() && numbers.size() < N) {
			comb.add(new ArrayList<>(numbers));
		}
		for (int i = depth; i < N; i++) {
			numbers.add(i);
			dfs(comb, numbers, i + 1);
			numbers.remove(numbers.size() - 1);
		}
	}
}