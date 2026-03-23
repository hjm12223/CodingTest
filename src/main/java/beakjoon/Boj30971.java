package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj30971 {
	static boolean[] visited;
	static int K;
	static int[] sweet;
	static int[] salty;
	static int[] sense;
	static int result = -1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		sweet = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		salty = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		sense = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		visited = new boolean[N];

		dfs(N, 0, new ArrayList<>());
		System.out.println(result);
	}

	private static void dfs(int N, int depth, List<Integer> list) {
		if (depth == N) {
			check(list);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				list.add(i);
				visited[i] = true;
				dfs(N, depth + 1, list);
				list.remove(list.size() - 1);
				visited[i] = false;
			}
		}
	}

	private static void check(List<Integer> list) {
		int gamchilFlavor = 0;
		for (int i = 1; i < list.size(); i++) {
			int prev = list.get(i - 1);
			int curr = list.get(i);
			if (sense[prev] * sense[curr] <= K)
				gamchilFlavor += sweet[prev] * salty[curr];
			else return;
		}
		result = Math.max(result, gamchilFlavor);
	}
}
