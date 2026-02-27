package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1966_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Queue<Node> q = new ArrayDeque<>();
			int count = 0;
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				q.offer(new Node(Integer.parseInt(st.nextToken()), i));
			}
			while (!q.isEmpty()) {
				Node current = q.poll();

				boolean hasHigher = false;
				for (Node node : q) {
					if (node.priority > current.priority) {
						hasHigher = true;
						break;
					}
				}

				if (hasHigher) {
					q.offer(current);
				} else {
					count++;
					if (current.order == M) {
						System.out.println(count);
						break;
					}
				}
			}

		}
	}

	private static class Node {
		int priority;
		int order;

		public Node(int priority, int order) {
			this.priority = priority;
			this.order = order;
		}
	}
}
