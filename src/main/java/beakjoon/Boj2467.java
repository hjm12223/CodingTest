package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2467 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int resultL = 0;
		int resultR = 0;

		int left = 0;
		int right = N - 1;
		int sum = Integer.MAX_VALUE;
		while (left < right) {
			int value = arr[left] + arr[right];
			if (Math.abs(value) < Math.abs(sum)) {
				sum = value;
				resultL = arr[left];
				resultR = arr[right];
			}
			if (value < 0) {
				left++;
			} else {
				right--;
			}
		}

		System.out.println(resultL + " " + resultR);
	}
}