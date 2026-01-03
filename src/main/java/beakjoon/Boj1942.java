package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int N = 3;
		for (int i = 0; i < 3; i++) {
			String[] arr = br.readLine().split(" ");
			String[] startTime = arr[0].split(":");
			String[] endTime = arr[1].split(":");

			int hour = Integer.parseInt(startTime[0]);
			int min = Integer.parseInt(startTime[1]);
			int sec = Integer.parseInt(startTime[2]);

			int endH = Integer.parseInt(endTime[0]);
			int endM = Integer.parseInt(endTime[1]);
			int endS = Integer.parseInt(endTime[2]);
			int result = 0;
			while (true) {

				int value = hour * 10000 + min * 100 + sec;
				if (value % 3 == 0) {
					result++;
				}

				if (hour == endH && min == endM && sec == endS) break;

				sec++;
				if (sec == 60) {
					sec = 0;
					min++;
				}
				if (min == 60) {
					min = 0;
					hour++;
				}
				if (hour == 24) {
					hour = 0;
				}
			}
			System.out.println(result);
		}
	}

}
