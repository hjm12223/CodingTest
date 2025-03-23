package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14621 {
	static List<List<Node>> graph = new ArrayList<>();
	static boolean[] isMan;
	static int[] dist;
	static int N;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken()); // 학교의 수
		int M = Integer.parseInt(st.nextToken()); // 노드의 수
		// MST 문제인데 이걸 어떻게 해야할까
		isMan = new boolean[N + 1];
		st = new StringTokenizer(br.readLine());
		visited = new boolean[N + 1];
		dist = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			isMan[i] = st.nextToken().equals("M");
		}
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			if (isMan[from] != isMan[to]) {
				graph.get(from).add(new Node(to, cost));
				graph.get(to).add(new Node(from, cost));
			}
		}
		prim();
	}

	private static void prim() {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		Arrays.fill(dist, Integer.MAX_VALUE);
		pq.offer(new Node(1, 0));
		dist[1] = 0;
		int totalCost = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (visited[curr.to]) continue;
			visited[curr.to] = true;
			totalCost += curr.cost;
			for (Node next : graph.get(curr.to)) {
				if (!visited[next.to]) {
					pq.offer(new Node(next.to, next.cost));
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				System.out.println(-1);
				return;
			}
		}
		System.out.println(totalCost == 0 ? -1 : totalCost);
	}

	private static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Node{" +
				"to=" + to +
				", cost=" + cost +
				'}';
		}
	}
}
