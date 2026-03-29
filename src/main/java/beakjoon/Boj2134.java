package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2134 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] structA = new int[N + 1];
		int[] structB = new int[M + 1];
		for (int i = 1; i <= N; i++) {
			structA[i] = Integer.parseInt(br.readLine());
		}

		for (int i = 1; i <= M; i++) {
			structB[i] = Integer.parseInt(br.readLine());
		}
		int i = 1;
		int j = 1;
		long result = 0;
		int total = 0;
		while (i != N + 1 && j != M + 1) {
			int cnt = 0;
			while (structA[i] != 0 && structB[j] != 0) {
				structA[i]--;
				structB[j]--;
				cnt++;
			}
			total += cnt;
			result += (long)cnt * (i + j);
			if (structA[i] == 0) i++;
			if (structB[j] == 0) j++;
		}
		System.out.println(total + " " + result);
	}
}
