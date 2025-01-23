package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj5214 {
	static int N, K, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 역의 개수
		K = Integer.parseInt(st.nextToken()); // 하이퍼 튜브가 연결된 역의 개수
		M = Integer.parseInt(st.nextToken()); // 하이퍼 튜브의 개수
		List<List<Integer>> graph = new ArrayList<>();

		for (int i = 0; i <= N + M; i++)
			graph.add(new LinkedList<>());

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				int station = Integer.parseInt(st.nextToken());
				graph.get(station).add(i + N);
				graph.get(i + N).add(station);
			}
		}
		System.out.println("graph = " + graph);
		System.out.println(bfs(graph));
	}

	private static int bfs(List<List<Integer>> graph) {
		Queue<Node> q = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.dist - o2.dist;
			}
		});
		boolean[] visited = new boolean[N + M + 1];
		q.offer(new Node(1, 1));
		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.curr == N) {
				return curr.dist;
			}
			for (Integer next : graph.get(curr.curr)) {
				if (!visited[next]) {
					visited[next] = true;
					if (next > N) {
						q.offer(new Node(next, curr.dist));
					} else {
						q.offer(new Node(next, curr.dist + 1));
					}
				}
			}
		}
		return -1;
	}

	private static class Node {
		int curr;
		int dist;

		public Node(int curr, int dist) {
			this.curr = curr;
			this.dist = dist;
		}
	}
}
