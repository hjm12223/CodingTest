package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj21278 {
	static int N, M;
	static List<List<Integer>> graph = new ArrayList<>();
	static List<List<Integer>> comb = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int value = Integer.MAX_VALUE;
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
			graph.get(to).add(from);
		}
		dfs(2, new ArrayList<>());

		List<int[]> stb = new ArrayList<>();

		for (int i = 0; i < comb.size(); i++) {
			int[] res = new int[3];
			Queue<int[]> q = new ArrayDeque<>();
			visited = new boolean[N + 1];
			List<Integer> com = comb.get(i);
			Integer first = com.get(0);
			Integer second = com.get(1);
			visited[first] = true;
			visited[second] = true;
			q.offer(new int[] {first, 1});
			q.offer(new int[] {second, 1});
			res[0] = first;
			res[1] = second;
			int result = bfs(q);
			if (value >= result) {
				res[2] = result;
				value = result;
				stb.add(res);
			}
		}
		stb.sort((o1, o2) -> {
			if (o1[2] == o2[2]) {
				return o1[0] - o2[0];
			}
			return o1[2] - o2[2];
		});
		int[] result = stb.get(0);
		System.out.println(result[0] + " " + result[1] + " " + result[2]);
	}

	private static int bfs(Queue<int[]> q) {
		int result = 0;
		while (!q.isEmpty()) {
			int[] node = q.poll();
			int curr = node[0];
			int cost = node[1];
			for (int next : graph.get(curr)) {
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, cost + 1});
					result += cost * 2;
				}
			}
		}
		return result;
	}

	private static void dfs(int r, List<Integer> list) {
		if (list.size() == r) {
			comb.add(new ArrayList<>(list));
			return;
		}
		for (int i = 1; i <= N; i++) {
			list.add(i);
			dfs(r, list);
			list.remove(list.size() - 1);
		}
	}
}
