package beakjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj10423 {
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		isVisited = new boolean[V + 1];
		int velopCnt = Integer.parseInt(st.nextToken()); // 발전소 갯수

		st = new StringTokenizer(br.readLine());

		Queue<Node> pq = new PriorityQueue<>();

		for (int i = 0; i < velopCnt; i++) {
			int development = Integer.parseInt(st.nextToken());
			isVisited[development] = true;
			pq.offer(new Node(development, 0));
		}

		List<List<Node>> list = new ArrayList<>();

		for (int i = 0; i <= V; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(from).add(new Node(to, cost));
			list.get(to).add(new Node(from, cost));
		}
		for (int i = 1; i <= V; i++) {
			if (isVisited[i]) {
				for (Node next : list.get(i)) {
					if (!isVisited[next.n]) {
						pq.offer(next);
					}
				}
			}
		}

		prim(pq, list, E, V);
	}

	private static void prim(Queue<Node> pq, List<List<Node>> list, int E, int v) {
		int cnt = 0;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (isVisited[node.n]) continue;
			isVisited[node.n] = true;
			cnt += node.c;
			for (Node next : list.get(node.n)) {
				int nextIdx = next.n;
				int nextCost = next.c;
				if (!isVisited[nextIdx]) {
					pq.offer(new Node(nextIdx, nextCost));
				}
			}
		}
		System.out.println(cnt);
	}

	private static class Node implements Comparable<Node> {
		int n;
		int c;

		public Node(int n, int c) {
			this.n = n;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}
	}
}
