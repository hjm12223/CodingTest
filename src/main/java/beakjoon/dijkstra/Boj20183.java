package beakjoon.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj20183 {
	static List<List<Node>> graph;
	static int N, M, A, B;
	static long C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());  // 교차로 개수
		M = Integer.parseInt(st.nextToken());  // 골목 개수
		A = Integer.parseInt(st.nextToken());  // 시작 교차로
		B = Integer.parseInt(st.nextToken());  // 도착 교차로
		C = Long.parseLong(st.nextToken());  // 가진 돈

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		long maxCost = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long cost = Long.parseLong(st.nextToken());
			graph.get(from).add(new Node(to, cost));
			graph.get(to).add(new Node(from, cost));
			maxCost = Math.max(maxCost, cost);  // 가장 큰 요금을 기록
		}

		// 이진 탐색
		long left = 0, right = maxCost, answer = -1;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (canReachWithMaxCost(mid)) {  // 최대 요금이 mid 이하인 경로가 존재하는가?
				answer = mid;  // 가능한 경로를 찾으면, mid 값을 기록
				right = mid - 1;  // 더 작은 요금이 가능한지 탐색
			} else {
				left = mid + 1;  // mid 이하로는 갈 수 없으니 더 큰 요금으로 탐색
			}
		}

		System.out.println(answer);
	}

	// 다익스트라 알고리즘을 이용하여 최대 요금이 maxCost 이하로 가능한지 확인
	static boolean canReachWithMaxCost(long maxCost) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		long[] dist = new long[N + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[A] = 0;
		pq.offer(new Node(A, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (dist[curr.index] < curr.cost) continue;

			for (Node next : graph.get(curr.index)) {
				if (next.cost > maxCost) continue;  // maxCost보다 큰 간선은 사용하지 않음
				long newDist = curr.cost + next.cost;
				if (newDist < dist[next.index] && newDist <= C) {
					dist[next.index] = newDist;
					pq.offer(new Node(next.index, newDist));
				}
			}
		}

		return dist[B] <= C;  // 목적지 B까지의 경로가 예산 C 이하인지 확인
	}

	static class Node implements Comparable<Node> {
		int index;
		long cost;

		public Node(int index, long cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.cost, o.cost);
		}
	}
}
