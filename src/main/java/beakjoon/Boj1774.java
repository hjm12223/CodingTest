package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1774 {
	static int[] parent;

	private static double getDistance(int[] a, int[] b) {
		long x = (long)a[0] - b[0];
		long y = (long)a[1] - b[1];
		return (long)Math.sqrt(x * x + y * y);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 우주신의 갯수
		int M = Integer.parseInt(st.nextToken()); // 이미 연결된 통로
		parent = new int[N + 1];
		int[][] arr = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			union(x, y);
		}
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.dist, o2.dist));
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				double distance = getDistance(arr[i], arr[j]);
				pq.offer(new Node(i, j, distance));
			}
		}
		double result = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (find(curr.x) != find(curr.y)) {
				union(curr.x, curr.y);
				result += curr.dist;
			}
		}
		System.out.printf("%.2f", result);
	}

	static int find(int x) {
		if (x != parent[x]) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	static void union(int a, int b) {
		int parent_a = find(a);
		int parent_b = find(b);
		if (parent_a != parent_b) {
			parent[parent_b] = parent_a;
		}

	}

	private static class Node {
		int x;
		int y;
		double dist;

		public Node(int x, int y, double dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
}
