package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1246 {
	static int[] eggs;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		eggs = new int[M];
		for (int i = 0; i < M; i++)
			eggs[i] = Integer.parseInt(br.readLine());

		Arrays.sort(eggs);

		int maxRevenue = 0;
		int price = 0;

		for (int i = 0; i < eggs.length; i++) {
			int cnt = M - i;
			int sell = Math.min(N, cnt);
			int revenue = eggs[i] * sell;
			if (revenue > maxRevenue) {
				maxRevenue = revenue;
				price = eggs[i];
			}
		}
		System.out.println(price + " " + maxRevenue);
	}
}

/*
mid 현재 살려고 하는 사람의
 */