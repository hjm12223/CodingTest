package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15558 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] firstLine = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int[] secondLine = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		boolean[][] isVisited = new boolean[2][N];
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.time - o2.time);
		q.offer(new Node(0, 0, 0));
		isVisited[0][0] = true;
		while (!q.isEmpty()) {
			Node curr = q.poll();

			if (curr.index >= N - 1) {
				System.out.println(1);
				return;
			}

			if (curr.index + 1 < N && !isVisited[curr.line][curr.index + 1] &&
				(curr.line == 0 && firstLine[curr.index + 1] != 0
					|| curr.line == 1 && secondLine[curr.index + 1] != 0)) {
				isVisited[curr.line][curr.index + 1] = true;
				q.offer(new Node(curr.index + 1, curr.line, curr.time + 1));
			}

			if (curr.index - 1 >= 0 && !isVisited[curr.line][curr.index - 1] &&
				(curr.line == 0 && firstLine[curr.index - 1] != 0
					|| curr.line == 1 && secondLine[curr.index - 1] != 0)) {
				isVisited[curr.line][curr.index - 1] = true;
				q.offer(new Node(curr.index - 1, curr.line, curr.time + 1));
			}

			if (curr.index + K >= N) {
				System.out.println(1);
				return;
			}
			if (curr.index + K < N && !isVisited[Math.abs(curr.line - 1)][curr.index + K] &&
				(curr.line == 0 && secondLine[curr.index + K] != 0
					|| curr.line == 1 && firstLine[curr.index + K] != 0)) {
				isVisited[Math.abs(curr.line - 1)][curr.index + K] = true;
				q.offer(new Node(curr.index + K, Math.abs(curr.line - 1), curr.time + 1));
			}

			if (curr.time < N) {
				firstLine[curr.time] = 0;
				secondLine[curr.time] = 0;
			}
		}
		System.out.println(0);
	}

	private static class Node {
		int index;
		int line;
		int time;

		public Node(int index, int line, int time) {
			this.index = index;
			this.line = line;
			this.time = time;
		}
	}
}
