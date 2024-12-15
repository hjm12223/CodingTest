package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1976 {
	static List<List<Integer>> graph = new ArrayList<>();
	static int N, M;
	static List<Integer> res;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 도시의 개수
		M = Integer.parseInt(br.readLine());

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int next = Integer.parseInt(st.nextToken());
				if (i == j) continue;
				if (next == 1) {
					graph.get(i + 1).add(j + 1);
				}
			}
		}
		int[] result = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		for (int i = 1; i <= N; i++) {
			bfs(i);
			int cnt = 0;
			for (int element : result) {
				if (res.contains(element)) {
					cnt++;
				}
			}
			if (cnt == result.length) {
				System.out.println("YES");
				return;
			}
		}
		System.out.println("NO");
	}

	private static void bfs(int index) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(index);
		res = new ArrayList<>();
		res.add(index);
		boolean[] visited = new boolean[N + 1];
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (int i = 0; i < graph.get(curr).size(); i++) {
				Integer next = graph.get(curr).get(i);
				if (!visited[next]) {
					q.offer(next);
					visited[next] = true;
					res.add(next);
				}
			}
		}
	}
}
