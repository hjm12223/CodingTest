package Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Prim {
	public static void main(String[] args) {
		int[][] graph = {{1, 2, 6}, {1, 3, 3}, {1, 4, 1}, {2, 5, 4}, {3, 4, 2}, {3, 5, 5}, {4, 5, 7}};

		List<List<Node>> list = new ArrayList<>();

		for (int i = 0; i <= graph.length; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < graph.length; i++) {
			int from = graph[i][0];
			int to = graph[i][1];
			int cost = graph[i][2];
			list.get(from).add(new Node(to, cost));
			list.get(to).add(new Node(from, cost));
		}

		prim(graph[0][0], list);
	}

	private static void prim(int start, List<List<Node>> list) {
		int total = 0;
		boolean[] visited = new boolean[list.size()];
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (visited[curr.index]) continue;
			visited[curr.index] = true;
			total += curr.cost;
			for (int i = 0; i < list.get(curr.index).size(); i++) {
				int nextIndex = list.get(curr.index).get(i).index;
				int nextCost = list.get(curr.index).get(i).cost;
				if (!visited[nextIndex])
					pq.offer(new Node(nextIndex, nextCost));
			}
		}
		System.out.println("total = " + total);
	}

	public static class Node implements Comparable<Node> {
		int index;
		int cost;

		public Node(int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}
