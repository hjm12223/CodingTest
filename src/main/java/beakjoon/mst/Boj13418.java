package beakjoon.mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13418 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 건물의 갯수
		int M = Integer.parseInt(st.nextToken()); // 간선의 갯수
		List<List<Node>> list = new ArrayList<>();

		for (int i = 0; i < M + 1; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken()); // 0 은 내리막길 1 은 오르막길
			if (cost == 0) cost = 1;
			else cost = 0;
			list.get(from).add(new Node(to, cost));
			list.get(to).add(new Node(from, cost));
		}
		Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
		});
		Queue<Node> reversPq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.cost - o1.cost;
			}
		});
		int maximum = prim(list, reversPq);
		int minimum = prim(list, pq);
		int result = (maximum * maximum) - (minimum * minimum);
		System.out.println(result);

	}

	private static int prim(List<List<Node>> list, Queue<Node> pq) {

		boolean[] isVisited = new boolean[list.size()];
		int total = 0;

		pq.offer(new Node(0, 0));

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (isVisited[curr.index]) continue;
			isVisited[curr.index] = true;
			total += curr.cost;
			for (int i = 0; i < list.get(curr.index).size(); i++) {
				int nextIndex = list.get(curr.index).get(i).index;
				int nextCost = list.get(curr.index).get(i).cost;
				if (!isVisited[nextIndex]) {
					pq.offer(new Node(nextIndex, nextCost));
				}
			}
		}
		return total;
	}

	public static class Node {
		int index;
		int cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

	}
}
