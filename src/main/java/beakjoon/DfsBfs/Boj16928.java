package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16928 {
	static int[] board = new int[101];
	static boolean[] visited = new boolean[101];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사다리의 수
		int M = Integer.parseInt(st.nextToken()); // 뱀의 수

		for (int i = 1; i <= 100; i++) board[i] = i;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			board[start] = end;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			board[start] = end;
		}

		System.out.println(bfs());
	}

	private static int bfs() {
		List<Integer> result = new ArrayList<>();
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {1, 0});
		visited[1] = true;
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			int move = curr[0];
			int count = curr[1];
			if (move == 100) {
				return count;
			}
			for (int dice = 1; dice <= 6; dice++) {
				int next = move + dice;
				if (next > 100 || visited[next]) continue;
				visited[next] = true;
				q.offer(new int[] {board[next], count + 1});
			}
		}
		return -1;
	}
}