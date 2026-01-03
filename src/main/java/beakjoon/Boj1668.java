package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1668 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());
		int left = 1;
		int right = 1;
		int l_v = arr[0];
		int r_v = arr[N - 1];
		for (int i = 0; i < N; i++) {
			if (arr[i] > l_v) {
				l_v = arr[i];
				left++;
			}
			if (arr[N - i - 1] > r_v) {
				right++;
				r_v = arr[N - i - 1];
			}
		}
		System.out.println(left);

		System.out.println(right);
	}
}
