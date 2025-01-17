package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj8979 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken()); // 알고싶은 국가
		Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o2.value - o1.value;
			}
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.parseInt(st.nextToken());
			StringBuilder sb = new StringBuilder();
			sb.append(st.nextToken()).append(st.nextToken()).append(st.nextToken());
			int value = Integer.parseInt(sb.toString());
			Node node = new Node(index, value);
			pq.offer(node);
		}

		int rank = 1;
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			int cnt = 0;
			if (curr.index == K) {
				System.out.println(rank);
				return;
			}
			if (pq.peek() != null && pq.peek().value == curr.value) {
				while (pq.peek() != null && pq.peek().value == curr.value) {
					Node poll = pq.poll();
					cnt++;
					if (poll.index == K) {
						System.out.println(rank);
						return;
					}
				}
				rank += cnt;
			}

			rank++;
		}
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
