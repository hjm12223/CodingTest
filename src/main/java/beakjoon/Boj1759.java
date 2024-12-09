package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1759 {
	static int R, N;
	static String[] arr;
	static StringBuilder sb = new StringBuilder();
	static String[] vowels = new String[] {"a", "e", "i", "o", "u"};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = br.readLine().split(" ");
		Arrays.sort(arr);
		dfs(0, new String[R], 0, 0);

		System.out.println(sb);
	}

	private static void dfs(int depth, String[] output, int vowelCnt, int constanceCnt) {
		if (depth == R) {
			if (vowelCnt >= 1 && constanceCnt >= 2) {
				for (int i = 0; i < R; i++) {
					sb.append(output[i]);
				}
				sb.append("\n");
			}
			return;
		}
		for (int i = 0; i < N; i++) {
			if (depth > 0 && arr[i].compareTo(output[depth - 1]) <= 0) continue;
			output[depth] = arr[i];
			if (isVowels(arr[i])) {
				dfs(depth + 1, output, vowelCnt + 1, constanceCnt);
			} else {
				dfs(depth + 1, output, vowelCnt, constanceCnt + 1);
			}
		}

	}

	private static boolean isVowels(String ch) {
		for (String vowel : vowels) {
			if (vowel.equals(ch))
				return true;
		}
		return false;
	}
}
