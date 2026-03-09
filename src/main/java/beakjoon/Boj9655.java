package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj9655 {
	/*
	탁자 위에 돌 N개가 있다.
	상근이와 창영이는 턴을 번갈아가면서 돌을 가져가며, 돌은 1개 또는 3개 가져갈 수 있다.
	마지막 돌을 가져가는 사람이 게임을 이기게 된다.

	dp[갯수] = ture = 내가 이기는 상태
	dp[갯수] = false = 내가 지는 상태

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] dp = new boolean[N + 1];
		dp[1] = true;
		dp[2] = false;
		dp[3] = true;
		for (int i = 4; i <= N; i++) {
			dp[i] = !dp[i - 3] || !dp[i - 1];
		}
		System.out.println(dp[N] ? "SK" : "CY");
	}

}
