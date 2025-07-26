package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Boj2138 {
	static int N;
	static boolean[] visited;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] arr1 = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int[] arr2 = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		while (check(q, arr1, arr2)) {
			for (int i = 0; i < q.size(); i++) {
				Integer curr = q.poll();
				System.out.println(curr);
				if (curr >= 1 && curr < N - 1) {
					arr2[curr - 1] = Math.abs(arr2[curr - 1] - 1);
					arr2[curr] = Math.abs(arr2[curr] - 1);
					arr2[curr + 1] = Math.abs(arr2[curr + 1] - 1);
					cnt++;
				} else if (curr == 0) {
					arr2[curr] = Math.abs(arr2[curr] - 1);
					arr2[curr + 1] = Math.abs(arr2[curr + 1] - 1);
					cnt++;
				} else if (curr == N - 1) {
					arr2[curr - 1] = Math.abs(arr2[curr - 1] - 1);
					arr2[curr] = Math.abs(arr2[curr] - 1);
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	private static boolean check(Queue<Integer> q, int[] arr2, int[] arr1) {
		for (int i = 0; i < N; i++) {
			if (arr2[i] != arr1[i]) {
				q.offer(i);
			}
		}
		System.out.println("arr1 = " + Arrays.toString(arr1));
		System.out.println("arr2 = " + Arrays.toString(arr2));
		return !q.isEmpty();
	}
}
