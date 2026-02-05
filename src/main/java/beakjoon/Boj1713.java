package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1713 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[] arr = new int[M];
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Node> pq = new PriorityQueue<>();

		Map<Integer, Integer> map = new HashMap<>();

		for (int i = 0; i < M; i++) {
			int value = Integer.parseInt(st.nextToken());
		}
	}

	static class Node implements Comparator<Node> {
		int value;
		int idx;
		int recommendation;

		public Node(int value, int idx, int recommendation) {
			this.value = value;
			this.idx = idx;
			this.recommendation = recommendation;
		}

		@Override
		public int compare(Node o1, Node o2) {
			if (o1.recommendation == o2.recommendation) {
				return o1.idx - o2.idx;
			} else {
				return o2.recommendation - o1.recommendation;
			}
		}
	}
	/*
	
	 */
}
