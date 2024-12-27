package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj20366 {
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] snows = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(snows);
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				int diff = snows[i] + snows[j];
				int left = i + 1;
				int right = j - 1;
				while (left < right) {
					int diff2 = snows[left] + snows[right];
					result = Math.min(result, Math.abs(diff - diff2));
					if (diff > diff2) {
						left++;
					} else if (diff2 > diff) {
						right--;
					} else {
						System.out.println(0);
						return;
					}
				}
			}
		}
		System.out.println(result);
	}
}
