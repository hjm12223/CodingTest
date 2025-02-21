package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj19939 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 공의 갯수
		int K = Integer.parseInt(st.nextToken()); // 바구니
		int mid = K * (K + 1) / 2;
		if (N < mid) {
			System.out.println(-1);
			return;
		}
		int[] bucket = new int[K];
		for (int i = 0; i < bucket.length; i++) {
			bucket[i] = i + 1;
		}
		int remaining = N - mid;
		int idx = K - 1;
		while (remaining > 0) {
			bucket[idx] += 1;
			remaining -= 1;
			idx -= 1;
			if (idx < 0) {
				idx = K - 1;
			}
		}

		System.out.println(bucket[K - 1] - bucket[0]);
		/*
		해당 문제는 N 개의 공이 K개의 바구니에 빠짐없이 담아야한다
		K = 최대 100,000 K^2 불가능
		N을 다 넣을려면 K(K+1) /2 의 바구니가 필요
		 */

	}
}
