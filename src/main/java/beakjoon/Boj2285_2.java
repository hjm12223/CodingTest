package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2285_2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		long[][] arr = new long[N][2];
		long mid = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long home = Long.parseLong(st.nextToken());
			long people = Long.parseLong(st.nextToken());

			arr[i][0] = home;
			arr[i][1] = people;
			mid += people;
		}
		mid = (mid + 1) / 2;
		long sum = 0;
		Arrays.sort(arr, (o1, o2) -> Long.compare(o1[0], o2[0]));
		for (int i = 0; i < N; i++) {
			sum += arr[i][1];
			if (sum >= mid) {
				System.out.println(arr[i][0]);
				return;
			}
		}
	}
}
