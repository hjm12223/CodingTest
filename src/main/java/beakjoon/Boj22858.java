package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj22858 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] S = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] D = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[] P = new int[N];
		for (int k = 0; k < K; k++) {
			for (int i = 0; i < N; i++) {
				P[D[i] - 1] = S[i];
			}
			System.arraycopy(P, 0, S, 0, N);
		}
		for (int i = 0; i < N; i++) {
			System.out.print(P[i] + " ");
		}
	}
	/*
	for i to N
		S[D[i]] = P[i]
	reverse
	for i to N
		P[D[i]-1] = S[i]
	 */
}
