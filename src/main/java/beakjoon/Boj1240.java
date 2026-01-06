package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1240 {
	static List<List<int[]>> tree = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++)
			tree.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			tree.get(from).add(new int[] {to, cost});
			tree.get(to).add(new int[] {from, cost});
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			bw.write(bfs(from, to) + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int bfs(int from, int to) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {from, 0});
		boolean[] visited = new boolean[N + 1];
		visited[from] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int idx = curr[0];
			int cost = curr[1];
			if (idx == to)
				return cost;
			for (int[] node : tree.get(idx)) {
				int next = node[0];
				int nextCost = node[1] + cost;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, nextCost});
				}
			}
		}
		return -1;
	}
}

