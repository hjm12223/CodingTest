package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17951 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int result = 0;
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int total = 0;

		for (int j : arr) {
			total += j;
		}

		int left = 0;
		int right = total;
		while (left < right) {
			int mid = (left + right) / 2;
			int sum = 0;
			int cnt = 0;
			for (int i = 0; i < arr.length; i++) {
				sum += arr[i];
				if (sum >= mid) {
					cnt++;
					sum = 0;
				}
			}
			if (cnt >= K) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(result);
	}
}