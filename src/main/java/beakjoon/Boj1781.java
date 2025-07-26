package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1781 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> {
			if (o1.deadLine == o2.deadLine) {
				return Long.compare(o2.cup, o1.cup);
			} else return o1.deadLine - o2.deadLine;
		});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int deadline = Integer.parseInt(st.nextToken());
			int cup = Integer.parseInt(st.nextToken());
			pq.offer(new Node(deadline, cup));
		}
		long result = 0;
		Queue<Long> candidate = new PriorityQueue<>();
		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			candidate.offer(curr.cup);
			if (candidate.size() > curr.deadLine) {
				candidate.poll();
			}
		}
		while (!candidate.isEmpty()) {
			result += candidate.poll();
		}
		System.out.println(result);
	}

	public static class Node {
		int deadLine;
		long cup;

		public Node(int deadLine, int cup) {
			this.deadLine = deadLine;
			this.cup = cup;
		}

		@Override
		public String toString() {
			return "deadLine = " + this.deadLine + " cup = " + cup;
		}
	}
}
