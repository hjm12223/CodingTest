package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Boj1595 {
	static ArrayList<int[]>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;

		int maxNode = 0;
		graph = new ArrayList[100000];
		for (int i = 1; i <= 10000; i++) graph[i] = new ArrayList<>();

		while ((line = br.readLine()) != null) {
			if (line.isEmpty()) break;
			String[] arr = line.split(" ");
			int a = Integer.parseInt(arr[0]);
			int b = Integer.parseInt(arr[1]);
			int c = Integer.parseInt(arr[2]);

			graph[a].add(new int[] {b, c});
			graph[b].add(new int[] {a, c});

			maxNode = Math.max(maxNode, Math.max(a, b));
		}

		int[] first = bfs(1, maxNode);
		int[] second = bfs(first[0], maxNode);

		System.out.println(second[1]);
	}

	static int[] bfs(int start, int maxNode) {
		boolean[] visited = new boolean[maxNode + 1];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {start, 0});
		visited[start] = true;

		int farNode = start;
		int maxDist = 0;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int node = cur[0];
			int dist = cur[1];

			if (dist > maxDist) {
				maxDist = dist;
				farNode = node;
			}

			for (int[] next : graph[node]) {
				if (!visited[next[0]]) {
					visited[next[0]] = true;
					q.offer(new int[] {next[0], dist + next[1]});
				}
			}
		}
		return new int[] {farNode, maxDist};
	}
}
