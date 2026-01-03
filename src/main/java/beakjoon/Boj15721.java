package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj15721 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine()); // 게임을 진행하는 사람 수
		int T = Integer.parseInt(br.readLine()); // 번째
		int N = Integer.parseInt(br.readLine()); // 구호 0 뻔 1 데기
		int b_cnt = 0;
		int d_cnt = 0;
		int cnt = 0;
		int recur = 2;
		while (true) {
			b_cnt += 1;
			if (check(N, b_cnt, T, cnt, d_cnt)) return;
			cnt = (cnt + 1) % A;

			d_cnt += 1;
			if (check(N, b_cnt, T, cnt, d_cnt)) return;
			cnt = (cnt + 1) % A;

			b_cnt += 1;
			if (check(N, b_cnt, T, cnt, d_cnt)) return;
			cnt = (cnt + 1) % A;

			d_cnt += 1;
			if (check(N, b_cnt, T, cnt, d_cnt)) return;
			cnt = (cnt + 1) % A;

			for (int i = 0; i < recur; i++) {
				b_cnt += 1;
				if (check(N, b_cnt, T, cnt, d_cnt)) return;
				cnt = (cnt + 1) % A;
			}
			for (int i = 0; i < recur; i++) {
				d_cnt += 1;
				if (check(N, b_cnt, T, cnt, d_cnt)) return;
				cnt = (cnt + 1) % A;
			}
			recur += 1;
		}
		/*
		 ‘뻔 – 데기 – 뻔 – 데기 – 뻔 – 뻔 – 데기 – 데기’ 1회차
		 */
	}

	private static boolean check(int N, int b_cnt, int T, int cnt, int d_cnt) {
		if (N == 0) {
			if (b_cnt == T) {
				System.out.println(cnt);
				return true;
			}
		} else {
			if (d_cnt == T) {
				System.out.println(cnt);
				return true;
			}
		}
		return false;
	}
}
