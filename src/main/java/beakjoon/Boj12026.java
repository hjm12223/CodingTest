package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj12026 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		char[] arr = br.readLine().toCharArray();
		Map<Character, Character> map = new HashMap<>();
		char[] orders = new char[] {'B', 'O', 'J'};
		for (int i = 0; i < orders.length; i++) {
			map.put(orders[i], orders[(i + 1) % orders.length]);
		}
		int[] dp = new int[N];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			if (dp[i] == Integer.MAX_VALUE) continue;
			for (int j = i + 1; j < N; j++) {
				if (arr[j] == map.get(arr[i])) {
					int cost = (j - i) * (j - i);
					dp[j] = Math.min(dp[j], dp[i] + cost);
				}
			}
		}
		System.out.println(dp[N - 1] == Integer.MAX_VALUE ? -1 : dp[N - 1]);
	}
}
/*
BOJ 거리는 보도블록 N개가 일렬로 놓여진 형태의 도로이다. 도로의 보도블록은 1번부터 N번까지 번호가 매겨져 있다.

스타트의 집은 1번에 있고, 링크의 집은 N번에 있다. 스타트는 링크를 만나기 위해서 점프해가려고 한다.

BOJ거리의 각 보도블록에는 B, O, J 중에 하나가 쓰여 있다. 1번은 반드시 B이다.

스타트는 점프를 통해서 다른 보도블록으로 이동할 수 있다. 이때, 항상 번호가 증가하는 방향으로 점프를 해야 한다. 만약, 스타트가 현재 있는 곳이 i번이라면, i+1번부터 N번까지로 점프를 할 수 있다. 한 번 k칸 만큼 점프를 하는데 필요한 에너지의 양은 k*k이다.

스타트는 BOJ를 외치면서 링크를 만나러 가려고 한다. 따라서, 스타트는 B, O, J, B, O, J, B, O, J, ... 순서로 보도블록을 밟으면서 점프를 할 것이다.

스타트가 링크를 만나는데 필요한 에너지 양의 최솟값을 구하는 프로그램을 작성하시오.
 */