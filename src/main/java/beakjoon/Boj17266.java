package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj17266 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 길이
		int M = Integer.parseInt(br.readLine()); // 설치할 수 있는 기둥의 수

		int[] light = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int left = 1;
		int right = N;
		int result = N;
		while (left < right) {
			int mid = (left + right) / 2;
			if (isPossible(mid, N, light)) {
				right = mid - 1;
				result = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(result);
	}

	private static boolean isPossible(int mid, int n, int[] lights) {
		int start = 0;
		for (int light : lights) {
			if (light - mid > start) {
				return false;
			}
			start = light + mid;
		}

		return start >= n;
	}
}
