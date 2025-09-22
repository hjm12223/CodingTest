package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj12931 {
	static int[] arr;
	static int[] res;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		res = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int result = 0;
		while (true) {
			boolean allZero = true;
			for (int num : res) {
				if (num != 0) {
					allZero = false;
					break;
				}
			}
			if (allZero) break;

			boolean isEven = false;
			for (int i = 0; i < N; i++) {
				if (res[i] % 2 != 0) {
					res[i]--;
					result++;
					isEven = true;
				}
			}

			if (!isEven) {
				for (int i = 0; i < N; i++) {
					res[i] /= 2;
				}
				result++;
			}
		}
		System.out.println(result);
	}
}
