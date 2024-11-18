package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj30804 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] tang = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			int fruit = Integer.parseInt(st.nextToken());
			tang[i] = fruit;
		}
		int left = 0;
		int right = 0;
		int type = 0;
		int maxCount = 0;
		int[] count = new int[100_001];
		while (right < N) {
			if (count[tang[right]] == 0) {
				type++;
			}
			count[tang[right]]++;
			while (type > 2) {
				count[tang[left]]--;
				if (count[tang[left]] == 0) {
					type--;
				}
				left++;
			}
			maxCount = Math.max(maxCount, right - left + 1);
			right++;
		}
		System.out.println(maxCount);
	}
}
