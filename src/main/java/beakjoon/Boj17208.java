package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj17208 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		/*
		모든 주문은 각각 치즈버거 요구 개수와 감자튀김 요구 개수를 의미하는 2개의 정수로 이루어진다.
		어떤 주문을 처리하기 위해서는 치즈버거와 감자튀김을 정확히 그 주문에서 요구하는 만큼 써야 한다.
		또한 주문이 들어온 순서와 관계없이 원하는 주문을 선택해 처리할 수 있으며,
		한번 처리한 주문은 사라지므로 같은 주문을 다시 처리하는 것은 불가능하다.
		아쉽게도 주방에 남아있는 것이 많지 않기 때문에 어떤 주문은 처리하지 못할 수도 있다.
		최선의 방법으로 주문을 선택해 처리한다면 최대 몇 개의 주문을 처리할 수 있을까?
		 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 주문의 수
		int M = Integer.parseInt(st.nextToken()); // 남은 치즈버거 개수
		int K = Integer.parseInt(st.nextToken()); // 남은 감자튀김 개수

		int[][] orders = new int[N][2];
		for (int i = 0; i < orders.length; i++) {
			st = new StringTokenizer(br.readLine());
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
		}
		int[][] dp = new int[M + 1][K + 1];
		for (int n = 0; n < N; n++) {
			int burger = orders[n][0];
			int potato = orders[n][1];
			for (int i = M; i >= 1; i--) {
				for (int j = K; j >= 1; j--) {
					if (i >= burger && j >= potato) {
						dp[i][j] = Math.max(dp[i][j], dp[i - burger][j - potato] + 1);
					}
				}
			}
		}
		int result = 0;
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= K; j++) {
				result = Math.max(result, dp[i][j]);
			}
		}
		System.out.println(result);
	}
}
