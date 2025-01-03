package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Boj2600 {
	static int[][] grundy;
	static int[] moves;
	static int MAX = 501;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		moves = new int[3];
		for (int i = 0; i < 3; i++) {
			moves[i] = Integer.parseInt(st.nextToken());
		}

		grundy = new int[MAX][MAX];
		for (int[] row : grundy) {
			Arrays.fill(row, -1);
		}

		for (int t = 0; t < 5; t++) {
			st = new StringTokenizer(br.readLine());
			int k1 = Integer.parseInt(st.nextToken());
			int k2 = Integer.parseInt(st.nextToken());

			int result = runGrundy(k1, k2);
			bw.write(result == 0 ? "B" : "A");
			bw.newLine();
		}

		bw.flush();
		bw.close();
	}

	private static int runGrundy(int k1, int k2) {

		if (k1 == 0 && k2 == 0) {
			return 0;
		}
		if (grundy[k1][k2] != -1) {
			return grundy[k1][k2];
		}
		Set<Integer> nextGrundy = new HashSet<>();
		for (int move : moves) {
			if (k1 >= move) {
				nextGrundy.add(runGrundy(k1 - move, k2));
			}
		}
		for (int move : moves) {
			if (k2 >= move) {
				nextGrundy.add(runGrundy(k1, k2 - move));
			}
		}
		int mem = 0;
		while (nextGrundy.contains(mem)) {
			mem++;
		}
		grundy[k1][k2] = mem;
		return mem;
	}
}
