package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj9084 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());// 동전의 수
			int[] coins = new int[N + 1];
			int[] dp = new int[10001];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			dp[0] = 1;
			/*
			동전 J 를 만들기 위해서 만약 J - coins[i] > 0 보다 크면 해당 코인으로는 동전을 만들 수 있거나 이전의 코인을 여러개 사용해서 만들 수 있다.
			그러므로 해당 코인을 dp[j] 에 저장된 값과 + dp[j - coins[i]] 의 값을 더하면 기존의 저장된 메모이제이션 된 코인들로 경우의 수를 모두 구할 수 있다.
			하지만 이러한 조건이 성립될려면 가장 작은 코인들을 준으로 오름차순이 되어있어야지 가능하다.

			 */
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= 10_000; j++) {
					if (j - coins[i] > 0) {
						dp[j] = dp[j] + dp[j - coins[i]];
					} else if (j - coins[i] == 0) {
						dp[j]++;
					}
				}
			}

			int target = Integer.parseInt(br.readLine());
			bw.write(String.valueOf(dp[target]));
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
}
