package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15691 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 회전벨트에 놓인 접시의 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		int max = 0;
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			int value = Integer.parseInt(br.readLine()); // 현재 들어오는 초밥 값

			if (!q.contains(value) && q.size() < k) {
				q.offer(value); // 큐에 초밥 추가

				// 쿠폰값을 계속해서 업데이트 해줘야함
				if (!q.contains(c)) {
					max = Math.max(max, q.size() + 1);
				} else {
					max = Math.max(max, q.size()); // 최대 값 업데이트
				}

				// 큐가 k개일 때 쿠폰 초밥이 없으면 +1
				if (q.size() == k && !q.contains(c)) {
					System.out.println(k + 1); // 쿠폰 초밥을 포함한 최대 초밥 수
					return;
				}
			} else {
				// 큐가 k개를 유지하기 위해 초과된 경우 제거
				if (q.size() == k) {
					q.poll();
				}

				// 큐에 중복된 값이 있으면 해당 값 제거
				// 왜냐하면 중복된 값이 들어온 순간 연속적으로 먹는것이 의미가 없으므로 중복된 값이 나올때 까지 빼줌
				while (q.contains(value)) {
					q.poll(); // 큐에서 제거
				}
				q.offer(value); // 새 초밥 추가
			}
		}

		// 마지막으로 k개가 채워진 경우에도 쿠폰 초밥 체크
		if (q.size() == k && !q.contains(c)) {
			System.out.println(max + 1);
		} else {
			System.out.println(max);
		}
	}
}