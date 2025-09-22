package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj1697 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Deque<int[]> q = new ArrayDeque<>();
		boolean[] visited = new boolean[100001];
		q.offer(new int[] {N, 0});
		int result = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int idx = curr[0];
			int cost = curr[1];
			if (idx == M) {
				result = Math.min(cost, result);
				continue;
			}
			if (idx < 0) continue;
			if (idx > M * 2 + 1) continue;
			if (visited[idx]) continue;
			visited[idx] = true;

			int next = idx + 1;
			if (!visited[next])
				q.offerLast(new int[] {next, cost + 1});
			int before = idx - 1;
			if (!visited[before])
				q.offerLast(new int[] {before, cost + 1});
			int teleport = idx * 2;
			if (visited[teleport])
				q.offerFirst(new int[] {teleport, cost + 1});
		}
	}
}
