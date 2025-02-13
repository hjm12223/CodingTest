package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj19949 {
	static int[] answer;
	static int[] young;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		answer = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::valueOf).toArray();
		young = new int[10];
		back(0);
		System.out.println(ans);
	}

	private static void back(int depth) {
		if (depth == 10) {
			int cnt = 0;
			for (int i = 0; i < 10; i++) {
				if (answer[i] == young[i]) cnt++;
			}
			if (cnt >= 5) {
				ans++;
			}
			return;
		}
		for (int i = 1; i <= 5; i++) {
			if (depth >= 2) {
				if (young[depth - 1] == i && young[depth - 2] == i) continue;
			}
			young[depth] = i;
			back(depth + 1);
		}

	}
}
