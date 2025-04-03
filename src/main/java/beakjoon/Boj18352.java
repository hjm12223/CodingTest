package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj18352 {
	static List<List<Node>> graph = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 도시의 수
		int M = Integer.parseInt(st.nextToken()); // 도로의 수
		int K = Integer.parseInt(st.nextToken()); // 거리정보
		int X = Integer.parseInt(st.nextToken()); // 출발도시의 번호

		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, 1));
		}
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		q.offer(new Node(X, 0));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> result = new ArrayList<>();

		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[X] = 0;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (curr.cost > K) continue;
			if (curr.cost == K) {
				result.add(curr.to);
				continue;
			}
			for (Node next : graph.get(curr.to)) {
				int cost = curr.cost + next.cost;
				if (dist[next.to] > cost) {
					dist[next.to] = cost;
					q.offer(new Node(next.to, cost));
				}
			}
		}
		if (result.isEmpty()) {
			System.out.println(-1);
			return;
		}

		result.sort((o1, o2) -> o1 - o2);
		for (int i = 0; i < result.size(); i++)
			bw.write(result.get(i) + "\n");
		bw.flush();
		bw.close();
	}

	private static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
