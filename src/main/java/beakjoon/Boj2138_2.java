package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2138_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] start = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int[] target = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int[] start2 = new int[N];
		System.arraycopy(start, 0, start2, 0, N);
		int cnt = 0;
		int cnt2 = 0;
		for (int i = 1; i < start.length; i++) {
			if (start[i - 1] != target[i - 1]) {
				if (i < start.length - 1) {
					start[i - 1] = Math.abs(start[i - 1] - 1);
					start[i] = Math.abs(start[i] - 1);
					start[i + 1] = Math.abs(start[i + 1] - 1);
					cnt++;
				} else {
					start[i - 1] = Math.abs(start[i - 1] - 1);
					start[i] = Math.abs(start[i] - 1);
					cnt++;
				}
			}
		}
		start2[0] = Math.abs(start2[0] - 1);
		start2[1] = Math.abs(start2[1] - 1);
		cnt2++;
		for (int i = 1; i < start2.length; i++) {
			if (start2[i - 1] != target[i - 1]) {
				if (i < start2.length - 1) {
					start2[i - 1] = Math.abs(start2[i - 1] - 1);
					start2[i] = Math.abs(start2[i] - 1);
					start2[i + 1] = Math.abs(start2[i + 1] - 1);
					cnt2++;
				} else {
					start2[i - 1] = Math.abs(start2[i - 1] - 1);
					start2[i] = Math.abs(start2[i] - 1);
					cnt2++;
				}
			}
		}
		int result = Integer.MAX_VALUE;
		if (check(start, target)) {
			result = Math.min(result, cnt);
		}
		if (check(start2, target)) {
			result = Math.min(result, cnt2);
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static boolean check(int[] start, int[] target) {
		for (int i = 0; i < start.length; i++) {
			if (start[i] != target[i]) return false;
		}
		return true;
	}
}
