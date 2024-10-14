package beakjoon.TwoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15691_2 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 회전벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] sushi = new int[N]; // 스시를 담아낼 배열
		int[] sushiTypes = new int[d + 1];
		int sushiCount = 0;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()); // 전처리단계
			sushi[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < k; i++) {
			if (sushiTypes[sushi[i]] == 0) sushiCount++;
			sushiTypes[sushi[i]]++;
		}

		int answer = sushiTypes[c] == 0 ? sushiCount + 1 : sushiCount;

		for (int i = 1; i < N; i++) {
			int removeSushi = sushi[i - 1];
			sushiTypes[removeSushi]--;

			if (sushiTypes[removeSushi] == 0) {
				sushiCount--;
			}

			int addSushi = sushi[(i + k - 1) % N];
			if (sushiTypes[addSushi] == 0) sushiCount++;
			sushiTypes[addSushi]++;
			
			int currentMax = sushiTypes[c] == 0 ? sushiCount + 1 : sushiCount;
			answer = Math.max(currentMax, answer);
		}
		System.out.println(answer);
	}
}
