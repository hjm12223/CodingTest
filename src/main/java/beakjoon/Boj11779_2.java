package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj11779_2 {
	static List<List<int[]>> graph = new ArrayList<>();
	static int[] prev;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		prev = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(from).add(new int[] {to, cost});
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());

		dijkstra(start, target);
	}

	private static void dijkstra(int start, int target) {
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		q.offer(new Node(start, 0));
		int[] dist = new int[N + 1];
		Arrays.fill(dist, (1 << 30));
		dist[start] = 0;
		while (!q.isEmpty()) {
			Node curr = q.poll();
			int idx = curr.idx;
			int cost = curr.cost;
			if (curr.cost > dist[curr.idx]) continue;
			if (curr.idx == target) break;
			for (int[] node : graph.get(idx)) {
				int nxIdx = node[0];
				int nxCost = cost + node[1];
				if (dist[nxIdx] > nxCost) {
					dist[nxIdx] = nxCost;
					prev[nxIdx] = idx;
					q.offer(new Node(nxIdx, nxCost));
				}
			}
		}
		Stack<Integer> stack = new Stack<>();
		int cur = target;
		StringBuilder sb = new StringBuilder();
		while (cur != 0) {
			stack.push(cur);
			cur = prev[cur];
		}
		int size = stack.size();
		while (!stack.isEmpty()) {
			sb.append(stack.pop()).append(" ");
		}
		System.out.println(dist[target]);
		System.out.println(size);
		System.out.println(sb);
	}

	static class Node {
		int idx;
		int cost;

		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}
}
