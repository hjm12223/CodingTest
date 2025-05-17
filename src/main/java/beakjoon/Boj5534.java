package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5534 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		String target = br.readLine();
		int result = 0;

		/*
		curr 을 기반으로 for문을 돌린다
		for문을 돌린 후
		 */

		for (int i = 0; i < N; i++) {
			String curr = br.readLine();

			for (int gap = 1; gap <= curr.length(); gap++) {
				int idx = 0;
				int tIdx = 0;

				while (idx < curr.length() && tIdx < target.length()) {
					if (curr.charAt(idx) == target.charAt(tIdx)) {
						tIdx++;
					}
					idx += gap;
				}

				if (tIdx == target.length()) {
					result++;
					break;
				}
			}
		}

		System.out.println(result);
	}
}