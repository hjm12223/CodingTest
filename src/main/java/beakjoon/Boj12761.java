package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12761 {
	static int[] moves = new int[] {1, -1};

	public static void main(String[] args) throws IOException {
		boolean[] visited = new boolean[100_001];
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {N, 0});

		while (!q.isEmpty()) {
			int[] node = q.poll();
			int curr = node[0];
			int cost = node[1];
			if (curr == M) {
				System.out.println(cost);
				return;
			}
			for (int move : moves) {
				int next = curr * move * A;
				if (next < 0 || next > 100_000) continue;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, cost + 1});
				}
			}
			for (int move : moves) {
				int next = curr * move * B;
				if (next < 0 || next > 100_000) continue;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, cost + 1});
				}
			}
			for (int move : moves) {
				int next = curr + move * A;
				if (next < 0 || next > 100_000) continue;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, cost + 1});
				}
			}
			for (int move : moves) {
				int next = curr + move * B;
				if (next < 0 || next > 100_000) continue;
				if (!visited[next]) {
					visited[next] = true;
					q.offer(new int[] {next, cost + 1});
				}
			}
			int next = curr + 1;
			if (next < 0 || next > 100_000) continue;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new int[] {next, cost + 1});
			}
			next = curr - 1;
			if (next < 0 || next > 100_000) continue;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new int[] {next, cost + 1});
			}
		}

	}
}
