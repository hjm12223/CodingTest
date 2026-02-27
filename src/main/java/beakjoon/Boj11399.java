package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj11399 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int result = 0;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += arr[i];
			result += sum;
		}
		System.out.println(result);
	}
}
