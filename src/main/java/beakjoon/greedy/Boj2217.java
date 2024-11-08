package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2217 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 로프의 개수

		// 로프의 중량 입력 받기
		List<Integer> ropes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			ropes.add(Integer.parseInt(st.nextToken()));
		}

		// 내림차순으로 정렬
		Collections.sort(ropes, Collections.reverseOrder());

		// 최대 중량 계산
		int maxWeight = 0;
		for (int i = 0; i < N; i++) {
			int weight = ropes.get(i) * (i + 1);
			maxWeight = Math.max(maxWeight, weight);  // 최대 중량 갱신
		}

		// 결과 출력
		System.out.println(maxWeight);
	}
}
