package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18243 {
	static int N, K;
	static List<List<Integer>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		for (int i = 1; i <= N; i++) {
			if (!bfs(i)) {
				System.out.println("Big World!");
				return;
			}
		}

		System.out.println("Small World!");
	}

	private static boolean bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		int[] dist = new int[N + 1];
		Arrays.fill(dist, -1);

		dist[start] = 0;
		q.offer(start);

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph.get(cur)) {
				if (dist[next] == -1) {
					dist[next] = dist[cur] + 1;
					if (dist[next] > 6) return false;
					q.offer(next);
				}
			}
		}

		for (int i = 1; i <= N; i++) {
			if (dist[i] == -1) return false;
		}
		return true;
	}
}
