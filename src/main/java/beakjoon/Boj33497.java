package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj33497 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int total = 0;

		int[] days = new int[M];
		int[] nights = new int[M];
		int[] absent = new int[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			nights[i] = Integer.parseInt(st.nextToken());
			if (days[i] + nights[i] > N) {
				System.out.println("NO");
				return;
			}
			absent[i] = N - (days[i] + nights[i]);
			total += absent[i];
		}
		if (total < N) {
			System.out.println("NO");
			return;
		}
		char[][] result = new char[N][M];
		for (int i = 0; i < N; i++) {
			Arrays.fill(result[i], '?');
		}
		int student = 0;
		for (int day = 0; day < M; day++) {
			int limit = Math.min(absent[day], N - student);
			for (int k = 0; k < limit; k++) {
				result[student][day] = 'X';
				student++;
			}
			if (student == N) break;
		}
		for (int day = 0; day < M; day++) {
			int need = days[day];
			for (int i = 0; i < N; i++) {
				if (result[i][day] == 'X') continue;
				if (need > 0) {
					result[i][day] = '+';
					need--;
				} else {
					result[i][day] = '-';
				}
			}
		}
		System.out.println("YES");
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.setLength(0);
			for (int j = 0; j < M; j++) {
				sb.append(result[i][j]);
			}
			System.out.println(sb);
		}
	}
}