package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class Boj2212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 센서
		int K = Integer.parseInt(br.readLine()); // 집중국
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		if (K >= N) { // 집중국이 센서보다 많을 경우
			System.out.println(0);
			return;
		}
		Arrays.sort(arr);

		Integer[] distance = new Integer[N - 1];
		for (int i = 0; i < N - 1; i++) {
			distance[i] = arr[i + 1] - arr[i];
		}
		Arrays.sort(distance, Collections.reverseOrder());
		int sum = 0;
		System.out.println("distance = " + Arrays.toString(distance));
		for (int i = K - 1; i < distance.length; i++) {
			sum += distance[i];
		}

		System.out.println(sum);
	}
}
