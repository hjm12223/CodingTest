package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20365 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split("");
		int redCnt = 1;
		int blueCnt = 1;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("R")) {
				for (int j = i; j < arr.length; j++) {
					if (!arr[j].equals("R")) {
						i = j;
						redCnt++;
						break;
					}
				}
			}
			if (arr[N - 1].equals("R")) redCnt++;

		}
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("B")) {
				for (int j = i; j < arr.length; j++) {
					if (!arr[j].equals("B")) {
						i = j;
						blueCnt++;
						break;
					}
				}
			}
			if (arr[N - 1].equals("B")) blueCnt++;
		}
		System.out.println(Math.min(redCnt, blueCnt));
	}
}
