package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj18111 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		int[][] blocks = new int[N][M];
		int minHeight = Integer.MAX_VALUE;
		int maxHeight = Integer.MIN_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				int height = Integer.parseInt(st.nextToken());
				blocks[i][j] = height;
				minHeight = Math.min(minHeight, height);
				maxHeight = Math.max(maxHeight, height);
			}
		}

		int bestTime = Integer.MAX_VALUE;
		int bestHeight = -1;

		for (int targetHeight = minHeight; targetHeight <= maxHeight; targetHeight++) {
			int time = 0;
			int inventory = B;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int diff = blocks[i][j] - targetHeight;

					if (diff > 0) {
						// Remove blocks
						time += diff * 2;
						inventory += diff;
					} else if (diff < 0) {
						// Add blocks
						time -= diff; // same as: time += (-diff)
						inventory += diff; // same as: inventory -= (-diff)
					}
				}
			}

			if (inventory >= 0 && time <= bestTime) {
				bestTime = time;
				bestHeight = targetHeight;
			}
		}

		System.out.println(bestTime + " " + bestHeight);
	}
}
