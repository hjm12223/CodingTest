package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2910 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Map<Integer, Node> map = new HashMap<>();
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> {
			if (o1.prior == o2.prior) {
				return o1.order - o2.order;
			}
			return o2.prior - o1.prior;
		});
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int key = Integer.parseInt(st.nextToken());
			if (!map.containsKey(key)) {
				Node node = new Node(key, 1, i);
				map.put(key, node);
				q.offer(node);
			} else {
				Node node = map.get(key);
				node.prior++;
				q.remove(node);
				q.offer(node);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			Node poll = q.poll();
			while (poll.prior-- > 0) {
				sb.append(poll.value + " ");
			}
		}
		System.out.println(sb);
	}

	static class Node {
		int value;
		int prior;

		int order;

		public Node(int value, int prior, int order) {
			this.value = value;
			this.prior = prior;
			this.order = order;
		}
	}
}
