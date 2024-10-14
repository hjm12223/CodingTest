package beakjoon.DfsBfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj13549 {
	public static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);  // 수빈이의 현재 위치
		int K = Integer.parseInt(input[1]);  // 동생의 현재 위치

		// 수빈이와 동생이 같은 위치에 있다면 즉시 0 출력
		if (N == K) {
			System.out.println(0);
			return;
		}

		// 방문 여부와 최단 시간을 기록할 배열
		int[] time = new int[100001];

		Arrays.fill(time,-1);

		// BFS를 위한 큐
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(N);
		time[N] = 0;  // 처음 위치를 0초로 설정

		while (!queue.isEmpty()) {
			int current = queue.poll();

			// 동생 위치에 도달한 경우
			if (current == K) {
				System.out.println(time[current]);  // 현재 위치까지의 시간을 출력
				return;
			}

			// 0초 후 이동 (순간이동)
			if (current * 2 <= 100000 && time[current * 2] == -1) {
				queue.offer(current * 2);
				time[current * 2] = time[current];  // 시간 변화 없음
			}

			// 1초 후 이동 (걷기)
			if (current + 1 <= 100000 && time[current + 1] == -1) {
				queue.offer(current + 1);
				time[current + 1] = time[current] + 1;
			}

			if (current - 1 >= 0 && time[current - 1] == -1) {
				queue.offer(current - 1);
				time[current - 1] = time[current] + 1;
			}
		}
	}
}

	/*
	수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다.
	수빈이는 걷거나 순간이동을 할 수 있다. 만약, 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다.
	순간이동을 하는 경우에는 0초 후에 2*X의 위치로 이동하게 된다.

	수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
	 */