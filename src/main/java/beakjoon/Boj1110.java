package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1110 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int value = Integer.parseInt(br.readLine());
		int result = value;
		int cnt = 0;
		do {
			int left = result / 10;
			int right = result % 10;
			int sum = left + right;
			result = (right * 10) + (sum % 10);
			cnt++;
		} while (result != value);
		System.out.println(cnt);
	}
}
