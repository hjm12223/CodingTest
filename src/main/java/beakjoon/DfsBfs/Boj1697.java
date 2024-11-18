package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1697 {
	static int[] dx = new int[] {1, -1, 2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		Queue<Integer> q = new LinkedList<>();

		q.offer(start);
		boolean[] isVisited = new boolean[100_002];
		int[] dist = new int[100_002];
		dist[start] = 1;
		while (!q.isEmpty() && dist[target] == 0) {
			Integer curr = q.poll();
			for (int i = 0; i < 3; i++) {
				int nextPlace = 0;
				if (dx[i] == 2) {
					nextPlace = curr * 2;
				} else {
					nextPlace = curr + dx[i];
				}
				if (nextPlace < 0 || nextPlace > 100_000 || isVisited[nextPlace]) continue;
				isVisited[nextPlace] = true;
				dist[nextPlace] += dist[curr] + 1;
				q.offer(nextPlace);
			}
		}
		System.out.println(dist[target] - 1);
	}
}
