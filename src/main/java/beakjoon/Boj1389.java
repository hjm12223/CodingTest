package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1389 {
	static int minValue = Integer.MAX_VALUE;
	static int resultIndex = Integer.MAX_VALUE;
	static int n;

	static List<Node> result = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken()); // 노드
		int m = Integer.parseInt(st.nextToken()); // 간선의 수

		List<List<Integer>> list = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(from).add(to);
			list.get(to).add(from);
		}
		int count = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (i == j) continue;
				count += dijkstra(list, i, j);
			}
			result.add(new Node(i, count));
			count = 0;
		}

		result.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.value - o2.value;
			}
		});
		System.out.println(result);
		System.out.println(result.get(0).index);
	}

	private static int dijkstra(List<List<Integer>> graph, int start, int target) {
		boolean[] isVisited = new boolean[n + 1];
		Queue<Integer> q = new LinkedList<>();
		isVisited[start] = true;
		int[] dist = new int[n + 1];
		dist[start] = 0;
		q.offer(start);
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			if (curr == target) {
				return dist[target];
			}
			for (int i = 0; i < graph.get(curr).size(); i++) {
				Integer nextFriend = graph.get(curr).get(i);
				if (!isVisited[nextFriend]) {
					dist[nextFriend] = dist[curr] + 1;
					isVisited[nextFriend] = true;
					q.offer(nextFriend);
				}
			}
		}
		return dist[target] - 1;
	}

	private static class Node {
		int index;
		int value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node{" +
				"index=" + index +
				", value=" + value +
				'}';
		}
	}
}
