package beakjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17835 {
	static Long[] dist;
	static List<List<Node>> list;

	static Queue<Node> pq;

	static int index = 0;
	static long value = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 도시의 수
		int E = Integer.parseInt(st.nextToken()); // 도시간 간선의 수
		int K = Integer.parseInt(st.nextToken()); // 면접장의 수

		list = new ArrayList<>(); // 인접리스트 구현

		for (int i = 0; i <= V; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			list.get(to).add(new Node(from, cost)); // 역방향 간선으로 추가
		}
		st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<>();

		dist = new Long[V + 1];
		Arrays.fill(dist, Long.MAX_VALUE);

		for (int i = 0; i < K; i++) {
			int place = Integer.parseInt(st.nextToken());
			dist[place] = 0L;
			pq.offer(new Node(place, 0)); // 모든 면접장을 큐에 넣음
		}

		dijkstra(); // 다익스트라 한 번만 호출

		System.out.println(index);
		System.out.println(value);
	}

	public static void dijkstra() {
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (dist[curr.index] < curr.cost) continue;

			for (Node next : list.get(curr.index)) {
				long nextCost = curr.cost + next.cost;
				if (dist[next.index] > nextCost) {
					dist[next.index] = nextCost;
					pq.offer(new Node(next.index, nextCost));
				}
			}
		}

		// 최댓값 및 해당 도시 인덱스 계산
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] > value && dist[i] != Integer.MAX_VALUE) {
				value = dist[i];
				index = i;
			} else if (dist[i] == value) {
				if (i < index) {
					index = i;
				}
			}
		}
	}

	public static class Node implements Comparable<Node> {
		int index;
		long cost;

		public Node(int index, long cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o1) {
			return Long.compare(this.cost, o1.cost);
		}
	}
}
