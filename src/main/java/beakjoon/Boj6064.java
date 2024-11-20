package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj6064 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(st.nextToken());
		for (int k = 0; k < T; k++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int lcm = (M * N) / GCD(M, N);

			// x를 기준으로 가능한 해 찾기
			int result = -1; // 초기값: 불가능한 경우
			for (int i = x; i <= lcm; i += M) { // x를 기준으로 증가
				if ((i - 1) % N + 1 == y) { // y를 만족하는지 확인
					result = i;
					break;
				}
			}
			bw.write(result + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int GCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}
}
