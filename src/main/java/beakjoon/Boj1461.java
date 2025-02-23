package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1461 {
	/*
	세준이는 도서관에서 일한다.
	도서관의 개방시간이 끝나서 세준이는 사람들이 마구 놓은 책을 다시 가져다 놓아야 한다. 세준이는 현재 0에 있고, 사람들이 마구 놓은 책도 전부 0에 있다.
	각 책들의 원래 위치가 주어질 때, 책을 모두 제자리에 놔둘 때 드는 최소 걸음 수를 계산하는 프로그램을 작성하시오.
	세준이는 한 걸음에 좌표 1칸씩 가며, 책의 원래 위치는 정수 좌표이다. 책을 모두 제자리에 놔둔 후에는 다시 0으로 돌아올 필요는 없다.
	그리고 세준이는 한 번에 최대 M권의 책을 들 수 있다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 책의 갯수
		int M = Integer.parseInt(st.nextToken()); // 한번에 들 수 있는 책의 무게

		Queue<Integer> left = new PriorityQueue<>((o1, o2) -> o1 - o2);
		Queue<Integer> right = new PriorityQueue<>((o1, o2) -> o2 - o1);

		st = new StringTokenizer(br.readLine());
		int[] books = new int[N];
		int maxDis = 0;
		for (int i = 0; i < N; i++) {
			books[i] = Integer.parseInt(st.nextToken());
			if (books[i] < 0) {
				left.offer(books[i]);
			} else {
				right.offer(books[i]);
			}
			maxDis = Math.max(Math.abs(books[i]), maxDis);
		}
		Arrays.sort(books);

		int result = 0;

		while (!right.isEmpty()) {
			Integer curr = right.poll();
			result += (curr * 2);
			for (int i = 0; i < M - 1; i++) {
				if (!right.isEmpty()) right.poll();
			}
		}
		while (!left.isEmpty()) {
			Integer curr = left.poll();
			result += Math.abs(curr * 2);
			for (int i = 0; i < M - 1; i++) {
				if (!left.isEmpty()) left.poll();
			}
		}
		System.out.println(result - maxDis);
	}
}
