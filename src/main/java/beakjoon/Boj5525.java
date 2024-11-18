package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5525 {
	public static void main(String[] args) throws IOException {
		int result = 0;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(br.readLine());
		String S = br.readLine();
		int count = 0;
		for (int i = 1; i < M - 1; i++) {
			if (S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
				count += 1;
				if (count >= N) {
					result++;
				}
				i++;
			} else {
				count = 0;
			}
		}
		System.out.println(result);
	}
}
