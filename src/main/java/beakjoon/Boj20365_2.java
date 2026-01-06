package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20365_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		char[] arr = br.readLine().toCharArray();
		int blueCnt = 0;
		int redCnt = 0;
		int r_result = 1;
		int b_result = 1;

		for (int i = 0; i < N; i++) {
			if (arr[i] == 'R') redCnt++;
			else blueCnt++;
		}
		int left = 0;
		int right;
		while (left < N) {
			right = left;
			if (arr[left] == 'R') {
				while (right < N && arr[right] == 'R') {
					right++;
				}
				left = right;
				r_result++;
			} else {
				left++;
			}
		}
		left = 0;
		while (left < N) {
			right = left;
			if (arr[left] == 'B') {
				while (right < N && arr[right] == 'B') {
					right++;
				}
				left = right;
				b_result++;
			} else {
				left++;
			}
		}
		System.out.println(Math.min(b_result, r_result));
	}
}
