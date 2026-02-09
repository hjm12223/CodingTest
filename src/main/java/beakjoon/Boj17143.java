package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj17143 {
	static int R, C, M, result;
	static Shark[][] arr;
	static int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static Queue<int[]> sharkIdx = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		result = 0;
		arr = new Shark[R][C];
		sharkIdx = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()) - 1;
			int z = Integer.parseInt(st.nextToken());
			arr[r][c] = new Shark(s, d, z);
			sharkIdx.offer(new int[] {r, c});
		}
		for (int i = 0; i < C; i++) {
			fishhook(i);
			arr = moveShark();
		}
		System.out.println(result);
	}

	private static Shark[][] moveShark() {
		Shark[][] sharks = new Shark[R][C];
		Queue<int[]> nextIdx = new ArrayDeque<>();
		while (!sharkIdx.isEmpty()) {
			int[] curr = sharkIdx.poll();
			int x = curr[0];
			int y = curr[1];
			if (arr[x][y] == null) continue;
			Shark shark = arr[x][y];
			int d = shark.direction;
			arr[x][y] = null;
			for (int i = 0; i < shark.speed; i++) {
				if (shark.direction < 2) {
					int nx = x + moves[d][0];
					if (nx < 0 || nx >= R) {
						d = (d == 0 ? 1 : 0);
						nx = x + moves[d][0];
					}
					x = nx;
				} else {
					int ny = y + moves[d][1];
					if (ny < 0 || ny >= C) {
						d = (d == 2 ? 3 : 2);
						ny = y + moves[d][1];
					}
					y = ny;
				}
			}
			shark.direction = d;
			if (sharks[x][y] != null) {
				if (shark.size > sharks[x][y].size) {
					sharks[x][y] = shark;
					nextIdx.offer(new int[] {x, y});
				}
			} else {
				sharks[x][y] = shark;
				nextIdx.offer(new int[] {x, y});
			}
		}
		sharkIdx = nextIdx;
		return sharks;
	}

	private static void fishhook(int col) {
		for (int i = 0; i < R; i++) {
			if (arr[i][col] != null) {
				result += arr[i][col].size;
				arr[i][col] = null;
				return;
			}
		}
	}

	/*
	1. 낚시꾼이 해당 C 에서 낚시를 함. O
	2. 해당 C에서 지표면에 가장 가까운 상어를 잡음(상어는 사라짐). O
	3. 없을 경우 다음으로 넘어감. O
	4. 1초가 지날시 수족관에 존재하는 상어들은 각 상태값에 알맞게 이동을 시작. O
	5. 만약 해당 칸에 상어가 여러마리 존재할 경우 크기가 가장 큰 상어가 해당 칸에 상어를 다 잡아먹음. O
	 */
	static class Shark {
		int speed;
		int direction;
		int size;

		public Shark(int speed, int direction, int size) {
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark{" +
				"speed=" + speed +
				", direction=" + direction +
				", size=" + size +
				'}';
		}
	}
}
