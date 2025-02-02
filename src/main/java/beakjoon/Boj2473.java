package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Boj2473 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		long[] arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		Arrays.sort(arr);

		long sum = Integer.MAX_VALUE;
		long[] result = new long[3];
		for (int i = 0; i < N - 2; i++) {
			int left = i + 1;
			int right = N - 1;

			while (left < right) {
				long currSum = arr[i] + arr[left] + arr[right];
				if (Math.abs(currSum) < Math.abs(sum)) {
					sum = currSum;
					result[0] = arr[i];
					result[1] = arr[left];
					result[2] = arr[right];
				}
				if (currSum < 0) {
					left++;
				} else if (currSum > 0) {
					right--;
				} else {
					break;
				}
			}
		}
		for (int i = 0; i < result.length; i++) {
			bw.write(result[i] + " ");
		}
		bw.flush();
		bw.close();
	}
}
