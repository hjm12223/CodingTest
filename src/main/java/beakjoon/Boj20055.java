package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20055 {
	static int N, K;
	static int[] durability;
	static boolean[] robots;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		durability = new int[2 * N];
		robots = new boolean[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			durability[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(simulate());
	}

	static int simulate() {
		int stage = 0;

		while (checkDurability() < K) {
			stage++;

			rotateBelt();
			moveRobots();
			addRobot();
		}

		return stage;
	}

	static void rotateBelt() {
		int lastValue = durability[durability.length - 1];
		for (int i = durability.length - 1; i > 0; i--) {
			durability[i] = durability[i - 1];
		}
		durability[0] = lastValue;

		for (int i = robots.length - 1; i > 0; i--) {
			robots[i] = robots[i - 1];
		}
		robots[0] = false;
		robots[robots.length - 1] = false;
	}

	static void moveRobots() {
		// 가장 먼저 올라간 로봇부터 이동 (역순으로)
		for (int i = N - 2; i >= 0; i--) {
			if (robots[i] && !robots[i + 1] && durability[i + 1] > 0) {
				robots[i] = false;
				robots[i + 1] = true;
				durability[i + 1]--;
			}
		}

		robots[N - 1] = false;
	}

	static void addRobot() {
		if (durability[0] > 0 && !robots[0]) {
			robots[0] = true;
			durability[0]--;
		}
	}

	static int checkDurability() {
		int zeroCount = 0;
		for (int d : durability) {
			if (d == 0) {
				zeroCount++;
			}
		}
		return zeroCount;
	}
}