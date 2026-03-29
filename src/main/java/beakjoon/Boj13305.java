package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj13305 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		int[] km = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // km
		int[] gas = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray(); // 가스
		long result = 0;
		for (int i = 0; i < gas.length - 1; i++) {
			int g = gas[i];
			while (i + 1 < km.length && g < gas[i + 1]) {
				result += (long)g * km[i];
				i++;
			}
			result += (long)g * km[i];
		}
		System.out.println(result);
	}
}
