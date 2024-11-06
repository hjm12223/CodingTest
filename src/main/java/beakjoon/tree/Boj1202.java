package beakjoon.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 보석의 수
		int k = Integer.parseInt(st.nextToken()); // 가방의 수

		Jewelry[] jewelry = new Jewelry[n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			jewelry[i] = new Jewelry
				(
					Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken())
				); // 보석의 값어치
		}
		int[] bag = new int[k];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			bag[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(bag);
		Arrays.sort(jewelry);

		System.out.println("jewelry = " + Arrays.toString(jewelry));
		System.out.println(Arrays.toString(bag));
	}

	private static class Jewelry implements Comparable<Jewelry> {
		int m;
		int v;

		public Jewelry(int m, int v) {
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jewelry o) {
			return this.m - o.m;
		}

		@Override
		public String toString() {
			return "Jewelry{" +
				"m=" + m +
				", v=" + v +
				'}';
		}
	}

}
