package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5014_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int F = Integer.parseInt(st.nextToken()); //총 층수
		int S = Integer.parseInt(st.nextToken()); //시작
		int G = Integer.parseInt(st.nextToken()); //목표
		int U = Integer.parseInt(st.nextToken()); //업
		int D = Integer.parseInt(st.nextToken()); //다운

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {S, 0});
		int[] dist = new int[F + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[S] = 0;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			if (curr[0] == G) {
				System.out.println(curr[1]);
				return;
			}
			int nx = curr[0] + U;
			if (nx > 0 && nx <= F && dist[nx] > curr[1] + 1) {
				dist[nx] = curr[1] + 1;
				q.offer(new int[] {nx, curr[1] + 1});
			}
			nx = curr[0] - D;
			if (nx > 0 && nx <= F && dist[nx] > curr[1] + 1) {
				dist[nx] = curr[1] + 1;
				q.offer(new int[] {nx, curr[1] + 1});
			}
		}
		System.out.println("use the stairs");
	}
}
