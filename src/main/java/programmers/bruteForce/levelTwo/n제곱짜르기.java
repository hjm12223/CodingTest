package programmers.bruteForce.levelTwo;

import java.util.Arrays;

public class n제곱짜르기 {
	public static void main(String[] args) {
		int[] solution = solution(4, 7, 14);
		System.out.println("solution = " + Arrays.toString(solution));
	}
	public static int[] solution(int n, long left, long right) {
		int[] result = new int[(int)(right - left + 1)];

		for (long i = left; i <= right; i++) {
			int row = (int)(i / n);
			int col = (int)(i % n);
			result[(int)(i - left)] = Math.max(row, col) + 1;
		}

		return result;
	}
}
