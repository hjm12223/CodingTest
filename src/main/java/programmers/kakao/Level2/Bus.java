package programmers.kakao.Level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Bus {
	public static void main(String[] args) {
		// System.out.println(solution(1, 1, 5, new String[] {"08:00", "08:01", "08:02", "08:03"}));
		// System.out.println(solution(2, 10, 2, new String[] {"09:10", "09:09", "08:00"})); // "09:09"
		// System.out.println(solution(2, 1, 2, new String[] {"09:00", "09:00", "09:00", "09:00"})); // "08:59"
		// System.out.println(solution(1, 1, 5, new String[] {"00:01", "00:01", "00:01", "00:01", "00:01"})); // "00:00"
		// System.out.println(solution(1, 1, 1, new String[] {"23:59"})); // "09:00"
		System.out.println(solution(10, 60, 45, new String[] {
					"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59",
					"23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59", "23:59"
				}
			)
		); // "18:00"
	}

	/*
	1. 셔틀은 09:00부터 총 n회 t분 간격으로 역에 도착하며, 하나의 셔틀에는 최대 m명의 승객이 탈 수 있다.
	2. 셔틀은 도착했을 때 도착한 순간에 대기열에 선 크루까지 포함해서 대기 순서대로 태우고 바로 출발한다.
	 	예를 들어 09:00에 도착한 셔틀은 자리가 있다면 09:00에 줄을 선 크루도 탈 수 있다.

	 단, 콘은 게으르기 때문에 같은 시각에 도착한 크루 중 대기열에서 제일 뒤에 선다.
	 또한, 모든 크루는 잠을 자야 하므로 23:59에 집에 돌아간다. 따라서 어떤 크루도 다음날 셔틀을 타는 일은 없다.
	 */
	public static String solution(int n, int t, int m, String[] timetable) {
		List<Integer> times = new ArrayList<>();
		for (String s : timetable) {
			int[] time = Arrays.stream(s.split(":")).mapToInt(Integer::parseInt).toArray(); // 타임을 만들어 준다
			times.add(time[0] * 60 + time[1]);
		}
		Collections.sort(times); // 오름차순 해주고
		int busTime = 540; // 버스의 시간을 표현
		int lastBusTime = 0; // 마지막 버스의 시간
		int busIndex = 0; // 현재 버스의 인덱스

		for (int i = 0; i < n; i++) {
			int remainSeat = m;
			while (busIndex < times.size() && times.get(busIndex) <= busTime && remainSeat > 0) {
				lastBusTime = times.get(busIndex);
				busIndex++;
				remainSeat--;
			}
			if (i == n - 1) {
				if (remainSeat > 0) {
					return formatting(busTime);
				} else {
					return formatting(lastBusTime - 1);
				}
			}
			busTime += t;
		}
		return "";
	}

	private static String formatting(int busTime) {
		int hour = busTime / 60;
		int minutes = busTime % 60;
		if (minutes < 10 && hour < 10) {
			return "0" + hour + ":" + "0" + minutes;
		} else if (minutes < 10) {
			return hour + ":0" + minutes;
		} else if (hour < 10) {
			return "0" + hour + ":" + minutes;
		} else return hour + ":" + minutes;
	}
}
