package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Boj29721 {
	static int N, K;
	static int[][] moves = new int[][] {{2, 0}, {0, 2}, {-2, 0}, {0, -2}};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Queue<int[]> q = new ArrayDeque<>();
		Set<String> s = new TreeSet<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			s.add(x + "_" + y);
			q.offer(new int[] {x, y});
		}
		int size = q.size();
		int cnt = 0;
		for (int i = 0; i < size; i++) {
			int[] curr = q.poll();
			for (int[] move : moves) {
				int nx = move[0] + curr[0];
				int ny = move[1] + curr[1];
				String key = nx + "_" + ny;
				if (nx <= 0 || ny <= 0 || nx > N || ny > N || s.contains(key)) continue;
				s.add(key);
				cnt++;
			}
		}
		System.out.println(cnt);
	}
}
