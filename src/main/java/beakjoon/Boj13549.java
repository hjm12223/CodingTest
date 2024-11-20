package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj13549 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int target = Integer.parseInt(st.nextToken());
		Deque<People> q = new ArrayDeque<>();

		q.offer(new People(start, 0));
		boolean[] isVisited = new boolean[100_001];
		isVisited[start] = true;
		int count = 0;
		while (!q.isEmpty()) {
			People curr = q.poll();

			if (curr.index == target) {
				System.out.println(curr.time);
			}

			if (curr.index * 2 <= 100_000 && !isVisited[curr.index * 2]) {
				isVisited[curr.index * 2] = true;
				q.offerFirst(new People(curr.index * 2, curr.time));

				System.out.println(count++);
			}

			if (curr.index - 1 >= 0 && !isVisited[curr.index - 1]) {
				isVisited[curr.index - 1] = true;
				q.offerLast(new People(curr.index - 1, curr.time + 1));
			}

			if (curr.index + 1 <= 100_000 && !isVisited[curr.index + 1]) {
				isVisited[curr.index + 1] = true;
				q.offerLast(new People(curr.index + 1, curr.time + 1));
			}
		}
	}

	public static class People {
		int index;
		int time;

		public People(int index, int time) {
			this.index = index;
			this.time = time;
		}
	}
}
