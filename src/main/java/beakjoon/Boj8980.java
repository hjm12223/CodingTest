package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj8980 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 마을 수
		int C = Integer.parseInt(st.nextToken()); // 트럭 용량

		int M = Integer.parseInt(br.readLine());
		Node[] nodes = new Node[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int amount = Integer.parseInt(st.nextToken());
			nodes[i] = new Node(from, to, amount);

		}

		Arrays.sort(nodes);

		int[] remain = new int[N + 1];
		Arrays.fill(remain, C);

		int result = 0;

		for (Node node : nodes) {
			int minCap = Integer.MAX_VALUE;
			for (int i = node.from; i < node.to; i++) {
				minCap = Math.min(minCap, remain[i]);
			}

			int load = Math.min(minCap, node.cost);
			result += load;

			for (int i = node.from; i < node.to; i++) {
				remain[i] -= load;
			}
		}

		System.out.println(result);
	}

	static class Node implements Comparable<Node> {
		int from;
		int to;
		int cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			if (this.to == o.to) return this.from - o.from;
			return this.to - o.to;
		}
	}
}
