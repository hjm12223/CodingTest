package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj31747 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		Deque<Integer> grade1 = new ArrayDeque<>();
		Deque<Integer> grade2 = new ArrayDeque<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int grade = Integer.parseInt(st.nextToken());
			if (grade == 1) {
				grade1.offer(i);
			} else {
				grade2.offer(i);
			}
		}
		int time = 0;
		while (!grade1.isEmpty() || !grade2.isEmpty()) {
			boolean isTake1 = !grade1.isEmpty() && grade1.peekFirst() < K;
			boolean isTake2 = !grade2.isEmpty() && grade2.peekFirst() < K;
			if (isTake1 && isTake2) {
				grade1.pollFirst();
				grade2.pollFirst();
				K += 2;
			} else if (isTake1) {
				grade1.pollFirst();
				K++;
			} else if (isTake2) {
				grade2.pollFirst();
				K++;
			}
			time++;
		}

		System.out.println(time);
	}
}