package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2457 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 꽃의 갯수
		List<Flower> flowers = new ArrayList<>(100000);
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int startMon = Integer.parseInt(st.nextToken());
			int startDay = Integer.parseInt(st.nextToken());
			int endMon = Integer.parseInt(st.nextToken());
			int endDay = Integer.parseInt(st.nextToken());
			if (endMon < 3 || startMon > 11) continue;
			flowers.add(new Flower(startMon * 100 + startDay, endMon * 100 + endDay));
			/*
			 꽃을 더해준다 ex)2월 27일 이면 200 + 30 = 227,  12월 17일 이면 12 * 100 + 17 = 1217 
			 */
		}
		flowers.sort((o1, o2) -> {
			/*
			 * 시작 날짜를 기준으로 오름차순 같을 경우 끝나는 날짜를 기준으로 내림차순
			 * 그래야지 오래 지속되는 날짜를 선정할 수 있기 떄문에
			 */
			if (o1.startDate == o2.startDate) {
				return o2.endDate - o1.endDate;
			}
			return o1.startDate - o2.startDate;
		});
		int start = 301; //요정이 요구한 시작일
		int maxEnd = 0; // Start 의 값을 변경해줄 임시 변수
		int end = 1130; // 요정이 요구한 마지막까지의 지속일
		int i = 0; // list 의 요소를 꺼내올 변수
		int answer = 0;
		while (start <= end) {
			boolean isFound = false; // 연속해서 심을 수 있는지에 대한 플래그
			while (i < N && flowers.get(i).startDate <= start) {
				maxEnd = Math.max(flowers.get(i).endDate, maxEnd);
				isFound = true;
				i++;
			}
			if (!isFound) {
				answer = 0;
				break;
			}
			answer++;
			start = maxEnd;
		}
		System.out.println(answer);
	}

	public static class Flower {
		int startDate;
		int endDate;

		public Flower(int startDate, int endDate) {
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
}