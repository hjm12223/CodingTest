package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj14503_2 {
	static int[][] arr;
	static int N, M;
	static boolean[][] isClean;
	static int[][] moves = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];
		isClean = new boolean[N][M];
		st = new StringTokenizer(br.readLine());

		Robot robot = new Robot(
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken()),
			Integer.parseInt(st.nextToken())
		);

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}
		while (true) {
			clear(robot); // 1 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			/*
			4칸 중 청소되지 않은 빈 칸이 있는 경우,
			반시계 방향으로
			90도 회전한다.
			바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			 */
			if (check(robot)) {
				doClean(robot);
			} else {
				directionChange(robot);
				if (arr[robot.x][robot.y] == 1) break;
			}
		}
		System.out.println(result);
	}

	private static void directionChange(Robot robot) {
		int nx = 0;
		int ny = 0;
		switch (robot.direction) {
			case 0:
				nx = robot.x + 1;
				ny = robot.y;
				break;
			case 1:
				nx = robot.x;
				ny = robot.y - 1;
				break;
			case 2:
				nx = robot.x - 1;
				ny = robot.y;
				break;
			case 3:
				nx = robot.x;
				ny = robot.y + 1;
				break;
		}
		robot.x = nx;
		robot.y = ny;
	}

	private static void doClean(Robot robot) {
		for (int i = 0; i < 4; i++) {
			robot.direction -= 1;
			if (robot.direction < 0) robot.direction = 3;
			int nx = 0;
			int ny = 0;
			switch (robot.direction) {
				case 0: // 북
					nx = robot.x - 1;
					ny = robot.y;
					break;
				case 1: // 동
					nx = robot.x;
					ny = robot.y + 1;
					break;
				case 2: // 남
					nx = robot.x + 1;
					ny = robot.y;
					break;
				case 3: // 서
					nx = robot.x;
					ny = robot.y - 1;
					break;
			}
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 1 || isClean[nx][ny]) continue;
			robot.x = nx;
			robot.y = ny;
			return;
		}
	}

	private static boolean check(Robot robot) {
		for (int[] move : moves) {
			int nx = move[0] + robot.x;
			int ny = move[1] + robot.y;
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 1) continue;
			if (!isClean[nx][ny]) return true;
		}
		return false;
	}

	private static void clear(Robot robot) {
		if (!isClean[robot.x][robot.y]) {
			isClean[robot.x][robot.y] = true;
			result++;
		}
	}

	static class Robot {
		int x;
		int y;
		int direction;
		/*
		0 ~ 3 , 북 동 남 서
		 */

		public Robot(int x, int y, int direction) {
			this.x = x;
			this.y = y;
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "Robot{" +
				"x=" + x +
				", y=" + y +
				", direction=" + direction +
				'}';
		}
	}
	/*
	현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
	현재 칸의 주변
	4칸 중 청소되지 않은 빈 칸이 없는 경우,
	바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
	바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
	현재 칸의 주변
	4칸 중 청소되지 않은 빈 칸이 있는 경우,
	반시계 방향으로
	90도 회전한다.
	바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
	1번으로 돌아간다.
	 */
}
