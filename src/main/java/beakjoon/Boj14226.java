package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Boj14226 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 총 만들어야하는 이모티콘의 갯수
		final int MAX = 2000;

		int[][] dist = new int[MAX + 1][MAX + 1];
		dist[1][0] = 0;
		for (int i = 0; i <= MAX; i++) {
			Arrays.fill(dist[i], -1);
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1, 0});
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int view = curr[0];
			int clip = curr[1];

			if (view == N) {
				System.out.println(dist[view][clip] + 1);
				return;
			}
			if (dist[view][view] == -1) {
				dist[view][view] = dist[view][clip] + 1;
				q.offer(new int[] {view, view});
			}
			if (view + clip <= MAX && dist[view + clip][clip] == -1) {
				dist[view + clip][clip] = dist[view][clip] + 1;
				q.offer(new int[] {view + clip, clip});
			}
			if (view > 0 && dist[view - 1][clip] == -1) {
				dist[view - 1][clip] = dist[view][clip] + 1;
				q.offer(new int[] {view - 1, clip});
			}
		}
	}
}