package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj3079_2 {
	static int[] times;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(st.nextToken()); // 입국 심사대
		int M = Integer.parseInt(st.nextToken()); // 친구의 수 심사대
		long maxTime = 0;
		times = new int[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			times[i] = time;
			maxTime = Math.max(time, maxTime);
		}
		long left = 0;
		long right = maxTime * M;
		while (left < right) {
			long mid = (left + right) / 2;
			long sum = 0;
			for (int i = 0; i < N; i++) {
				sum += mid / times[i];
				if (sum >= M) break;
			}
			if (sum >= M) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		bw.write(String.valueOf(left));
		bw.flush();
		bw.close();
	}
}
