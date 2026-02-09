package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9205 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			Node[] nodes = new Node[N + 2];
			for (int i = 0; i < N + 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int startX = Integer.parseInt(st.nextToken());
				int startY = Integer.parseInt(st.nextToken());
				nodes[i] = new Node(startX, startY);
			}
			boolean[] visited = new boolean[N + 2];
			Queue<Integer> q = new ArrayDeque<>();

			q.offer(0);
			visited[0] = true;
			boolean isHappy = false;
			while (!q.isEmpty()) {
				Integer curr = q.poll();

				if (curr == N + 1) {
					isHappy = true;
					break;
				}
				for (int i = 0; i < N + 2; i++) {
					if (!visited[i] && getDistance(nodes[curr], nodes[i]) <= 1000) {
						visited[i] = true;
						q.offer(i);
					}
				}
			}
			sb.append(isHappy ? "happy\n" : "sad\n");
		}
		System.out.println(sb);
	}

	static int getDistance(Node a, Node b) {
		return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
