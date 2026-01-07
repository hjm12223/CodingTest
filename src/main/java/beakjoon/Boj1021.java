package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj1021 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		Deque<Integer> q = new ArrayDeque<>();
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			q.offer(i);
		}
		int result = 0;
		for (int i = 0; i < M; i++) {

			int target = Integer.parseInt(st.nextToken());

			int idx = 0;
			for (Integer x : q) {
				if (target == x) break;
				idx++;
			}

			int size = q.size();
			int left = idx;
			int right = size - idx;
			/*
			left = 14
			right = 16
			 */
			if (left <= right) {
				for (int j = 0; j < left; j++) {
					q.addLast(q.pollFirst());
					result++;
				}
			} else {
				for (int j = 0; j < right; j++) {
					q.addFirst(q.pollLast());
					result++;
				}
			}
			q.pollFirst();
		}
		System.out.println(result);
	}

}

