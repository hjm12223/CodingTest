package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj3165 {
	public static void main(String[] args) throws IOException {
		/*
		1. 일의 자릿수 부터 5를 넣는게 효율적이다
		12345 6
		N = 12345000
		2. 만약 N / 5 - K > 1을 초과할경우 12345055
		*/
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		while (true) {
			if (containsFiveCount(N) >= K) {
				System.out.println(N);
				break;
			}
			N++; // N을 증가시켜 조건 만족하는 숫자 찾기
		}
	}

	// 숫자 내에 '5'가 몇 번 포함되어 있는지 세는 함수
	private static int containsFiveCount(long num) {
		int count = 0;
		while (num > 0) {
			if (num % 10 == 5) {
				count++;
			}
			num /= 10;
		}
		return count;
	}
}
