package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18232 {
	static int[] moves = new int[] {1, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 총 길이
		int M = Integer.parseInt(st.nextToken()); // 텔레포트의 개숫

		st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // 시작
		int E = Integer.parseInt(st.nextToken()); // 도착

		List<List<Integer>> teleports = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			teleports.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			teleports.get(from).add(to);
			teleports.get(to).add(from);

		}

		boolean[] visited = new boolean[N + 1];
		visited[S] = true;
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(S, 0));

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.curr == E) {
				System.out.println(curr.move);
				return;
			}

			if (!teleports.get(curr.curr).isEmpty()) {
				for (int teleport : teleports.get(curr.curr)) {
					if (!visited[teleport]) {
						visited[teleport] = true;
						q.offer(new Node(teleport, curr.move + 1));
					}
				}
			}
			for (int move : moves) {
				int nextN = move + curr.curr;
				if (nextN > N || nextN < 0) continue;
				if (!visited[nextN]) {
					visited[nextN] = true;
					q.offer(new Node(nextN, curr.move + 1));
				}
			}
		}
	}

	static class Node {
		int curr;
		int move;

		public Node(int curr, int move) {
			this.curr = curr;
			this.move = move;
		}
	}
}
