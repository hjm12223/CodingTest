package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17615 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = br.readLine().toCharArray();

		int leftR = 0, leftB = 0, rightR = 0, rightB = 0;

		char firstColor = arr[0];
		for (int i = 0; i < N; i++) {
			if (arr[i] == firstColor) {
				if (firstColor == 'R') leftR++;
				else leftB++;
			} else break;
		}

		char lastColor = arr[N - 1];
		for (int i = N - 1; i >= 0; i--) {
			if (arr[i] == lastColor) {
				if (lastColor == 'R') rightR++;
				else rightB++;
			} else break;
		}

		int totalR = 0, totalB = 0;
		for (char c : arr) {
			if (c == 'R') totalR++;
			else totalB++;
		}

		int result = Math.min(totalR - leftR, totalR - rightR);
		result = Math.min(result, Math.min(totalB - leftB, totalB - rightB));

		System.out.println(result);
	}
}
