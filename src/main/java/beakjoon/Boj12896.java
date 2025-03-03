package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12896 {
	static List<List<Node>> graph = new ArrayList<>();
	static int result = Integer.MAX_VALUE;
	static int farthestNode, maxDist;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 노드의 갯수
		// 현재 연결된 N-1 개의 간선이 존재
		// 해당 간선의 정보를 기반으로 각 노드에서 MST 를 시행하였을때 가장 최소치의 값이 나온 노드가 정답
		// (NlogN) * N = 500억 시간복잡도를 고려안함
		//
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 1));
			graph.get(to).add(new Node(from, 1));
		}
		bfs(1);
		maxDist = 0;
		bfs(farthestNode);
		System.out.println((maxDist + 1) / 2);
	}

	private static void bfs(int start) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(start, 0));
		boolean[] visited = new boolean[N + 1];
		visited[start] = true;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.value > maxDist) {
				maxDist = curr.value;
				farthestNode = curr.to;
			}
			for (Node next : graph.get(curr.to)) {
				if (!visited[next.to]) {
					q.offer(new Node(next.to, next.value + curr.value));
					visited[next.to] = true;
				}
			}
		}
	}

	private static void prim(int start) {
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
		pq.offer(new Node(start, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[start] = 0;
		int maxValue = 0;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			maxValue = Math.max(curr.value, maxValue);
			for (Node next : graph.get(curr.to)) {
				int nxValue = next.value + curr.value;
				if (dist[next.to] > nxValue) {
					dist[next.to] = nxValue;
					pq.offer(new Node(next.to, nxValue));
				}
			}
		}
		result = Math.min(maxValue, result);
	}

	private static class Node {
		int to;
		int value;

		public Node(int to, int value) {
			this.to = to;
			this.value = value;
		}
	}
}
