package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12851 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] isVisited = new int[100_001];
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Queue<Node> q = new LinkedList<>();
		int result = 0;

		Arrays.fill(isVisited, Integer.MAX_VALUE);
		q.offer(new Node(N, 0));
		int minTime = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			if (isVisited[curr.curr] >= curr.time) {
				isVisited[curr.curr] = curr.time;
			} else continue;
			if (curr.curr == M && minTime >= curr.time) {
				minTime = curr.time;
				result++;
				continue;
			}

			if (curr.curr * 2 < 100_001) {
				q.offer(new Node(curr.curr * 2, curr.time + 1));
			}
			if (curr.curr + 1 < 100_001) {
				q.offer(new Node(curr.curr + 1, curr.time + 1));
			}
			if (curr.curr - 1 >= 0) {
				q.offer(new Node(curr.curr - 1, curr.time + 1));
			}
		}
		System.out.println(minTime);
		System.out.println(result);
	}

	private static class Node {
		int curr;
		int time;

		public Node(int curr, int time) {
			this.curr = curr;
			this.time = time;
		}
	}
}
