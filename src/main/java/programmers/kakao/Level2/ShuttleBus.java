package programmers.kakao.Level2;

import java.util.Arrays;

public class ShuttleBus {
	/*
	빡구현문제
	 */
	private static final int NINE_TO_SEC = 32400;
	public static void main(String[] args) {
		solution(1,1,5,new String[]{"08:00", "08:01", "08:02", "08:03"});
		// solution(1,1,5, new String[]{"00:02", "00:02", "00:02", "00:02", "00:02"});
	}
	public static String solution(int n, int t, int m, String[] timetable) {
		int waitingPerson = timetable.length;
		int[] secWaitingArr = timeToSec(timetable);
		Arrays.sort(timetable);

		/*
	    n 운행횟수
	    m 탑승할 수 있는 크루원
		 */

		if (m*n >= waitingPerson){ // 기다리는 사람보다 작을 경우는
			String str = intTimeToStringTime(secWaitingArr[waitingPerson - 1]);
		}else if (secWaitingArr[waitingPerson-1] < NINE_TO_SEC){

		}else {
			return timetable[waitingPerson-1];
		}


		String answer = "";
		return answer;
	}

	private static int[] timeToSec(String[] timetable) {
		int[] secWaitingArr = new int[timetable.length];
		for (int i = 0 ; i< timetable.length; i++){
			String[] hourAndMin = timetable[i].split(":");

			String hour = hourAndMin[0];
			String min = hourAndMin[1];

			secWaitingArr[i] = (Integer.parseInt(hour) * 60 *60) + (Integer.parseInt(min) * 60);
		}
		Arrays.sort(secWaitingArr);
		return secWaitingArr;
	}
	
	private static String intTimeToStringTime(int sec){
		int minute = (sec -60) % 60;
		int hour = ((sec - 60) /60) / 60;
		StringBuilder str = new StringBuilder();
		str.append(hour);
		if (hour < 10){
			str.insert(0,"0");;
			str.append(":");
		}else {
			str.append(":");
		}

		str.append(minute);
		if (minute < 10) {
			str.insert(3, "0");
		}
		return str.toString();
	}
}

/*
셔틀 운행 횟수 n,
셔틀 운행 간격 t,
한 셔틀에 탈 수 있는 최대 크루 수 m,
크루가 대기열에 도착하는 시각을 모은 배열 timetable 이 입력으로 주어진다.
셔틀은 09:00 부터 운행을 시작

전제는 -> 콘은 항상 마지막에 셔틀을 탄다
		 콘은 무조건 셔틀을 타야한다
 */