package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj13549_2 {
	static int[] moves = new int[] {1, -1};
	static int INF = 100_000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		int[] dist = new int[INF + 1];
		Deque<int[]> q = new ArrayDeque<>();

		Arrays.fill(dist, (1 << 30));
		dist[start] = 0;
		q.offerLast(new int[] {start, 0});
		while (!q.isEmpty()) {
			int[] node = q.pollFirst();
			int curr = node[0];
			int point = node[1];
			if (curr == target) {
				System.out.println(point);
				return;
			}
			if (curr > INF) continue;
			int teleport = curr * 2;
			if (teleport <= INF && dist[teleport] > dist[curr]) {
				dist[teleport] = dist[curr];
				q.offerFirst(new int[] {curr * 2, point});
			}
			for (int move : moves) {
				int next = curr + move;
				if (next >= 0 && next <= INF && dist[next] > dist[curr] + 1) {
					dist[next] = dist[curr] + 1;
					q.offerLast(new int[] {next, point + 1});
				}
			}
		}
	}
}
