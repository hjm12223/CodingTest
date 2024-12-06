package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2847 {
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		int temp = N - 1;
		int lastMaxValue = arr[temp];
		while (temp-- > 0) {
			if (lastMaxValue <= arr[temp]) {
				while (arr[temp] >= lastMaxValue) {
					arr[temp]--;
					result++;
				}
			}
			lastMaxValue = arr[temp];
		}
		System.out.println(result);
	}
}
