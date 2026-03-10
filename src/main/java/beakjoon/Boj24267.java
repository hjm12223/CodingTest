package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj24267 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int result = Integer.parseInt(br.readLine());
		long sum = 0;
		int[] arr = new int[result + 1 - 2];
		for (int i = 1; i <= result - 2; i++) {
			arr[i] = i + arr[i - 1];
		}
		for (int i = 1; i <= result - 2; i++) {
			sum += arr[i];
		}
		System.out.println();
		System.out.println(sum);
		System.out.println(3);
	}

}
