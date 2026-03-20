package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] arr = new int[31];
		for (int i = 1; i <= 28; i++) {
			int value = Integer.parseInt(br.readLine());
			arr[value]++;
		}
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 1; i <= 30; i++) {

			if (arr[i] != 1) {
				min = Math.min(min, i);
				max = Math.max(max, i);
			}
		}
		System.out.println(min + "\n" + max);
	}
}