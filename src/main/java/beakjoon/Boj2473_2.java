package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2473_2 {
	static long lastResult = Long.MAX_VALUE;
	static long[] answer = new long[3];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		Arrays.sort(arr);

		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;
			while (left < right) {
				long sum = arr[i] + arr[left] + arr[right];
				if (Math.abs(sum) < lastResult) {
					lastResult = Math.abs(sum);
					answer[0] = arr[i];
					answer[1] = arr[left];
					answer[2] = arr[right];
				}
				if (sum > 0) {
					right--;
				} else if (sum < 0) {
					left++;
				} else {
					break;
				}
			}
		}
		System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
	}
}
