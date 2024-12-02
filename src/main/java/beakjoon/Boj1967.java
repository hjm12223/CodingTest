package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1967 {
	static List<List<Node>> graph;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 노드의 수

		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
		}
		Node start = bfs(1, graph);
		Node result = bfs(start.curr, graph);
		System.out.println(result.value);
	}

	private static Node bfs(int start, List<List<Node>> graph) {

		boolean[] visited = new boolean[N + 1];
		visited[start] = true;

		Queue<Node> pq = new LinkedList<>();
		Node startNode = new Node(start, 0);
		pq.offer(startNode);

		while (!pq.isEmpty()) {
			Node node = pq.poll();
			for (int i = 0; i < graph.get(node.curr).size(); i++) {
				Node nextNode = graph.get(node.curr).get(i);
				if (!visited[nextNode.curr]) {
					int nextValue = nextNode.value + node.value;
					visited[nextNode.curr] = true;
					pq.offer(new Node(nextNode.curr, nextValue));
					if (nextValue > startNode.value) {
						startNode = new Node(nextNode.curr, nextValue);
					}
				}
			}
		}
		return startNode;
	}

	private static class Node {
		int curr;
		int value;

		public Node(int curr, int value) {
			this.curr = curr;
			this.value = value;
		}
	}
}
