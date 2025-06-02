package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17179 {
	static int[] cakes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 자르는 횟수 목록의 길이
		int M = Integer.parseInt(st.nextToken()); // 자를 수 있는 지점 수
		int L = Integer.parseInt(st.nextToken()); // 케이크 길이

		cakes = new int[M + 2];
		for (int i = 1; i <= M; i++) {
			cakes[i] = Integer.parseInt(br.readLine());
		}
		cakes[0] = 0;
		cakes[M + 1] = L;

		int[] commands = new int[N];
		for (int i = 0; i < N; i++) {
			commands[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(cakes);
		for (int command : commands) {
			int left = 1;
			int right = L;
			int result = 0;
			while (left <= right) {
				int mid = (left + right) / 2;
				if (isCut(mid, command + 1)) {
					System.out.println(mid);
					result = mid;
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static boolean isCut(int mid, int pieceCake) {
		int last = 0;
		int cnt = 0;
		for (int i = 1; i < cakes.length; i++) {
			if (mid <= cakes[i] - cakes[last]) {
				cnt++;
				last = i;
			}
		}
		return cnt >= pieceCake;
	}
}
