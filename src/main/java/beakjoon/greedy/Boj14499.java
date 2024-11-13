package beakjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {
	static int N, M, x, y, K;
	static int[][] map;
	static int[] dice = new int[6]; // 주사위의 6면: 상, 북, 동, 서, 남, 하
	// 동 서 남 북
	static int[] dx = new int[] {0, 0, -1, 1};
	static int[] dy = new int[] {1, -1, 0, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
		y = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		// 지도 값 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 명령어 입력
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		// 명령어 실행
		for (int i = 0; i < K; i++) {
			int command = Integer.parseInt(st.nextToken()) - 1; // 명령어는 1부터 시작하므로 0부터 시작하도록 조정
			int nx = x + dx[command];
			int ny = y + dy[command];

			// 지도 범위를 벗어나면 무시
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

			// 주사위 이동
			moveDice(command);

			// 주사위 위치 갱신
			x = nx;
			y = ny;

			// 지도와 주사위의 바닥면 값 변경
			if (map[x][y] == 0) {
				map[x][y] = dice[5]; // 주사위의 바닥면 값을 지도에 복사
			} else {
				dice[5] = map[x][y]; // 지도의 값을 주사위 바닥면으로 복사
				map[x][y] = 0; // 지도의 값은 0으로 초기화
			}

			// 주사위의 윗면 값을 결과에 추가
			sb.append(dice[0]).append("\n");
		}

		// 결과 출력
		System.out.print(sb.toString());
	}

	// 주사위를 주어진 방향으로 굴리는 함수
	public static void moveDice(int direction) {
		int[] temp = dice.clone();
		switch (direction) {
			case 0: // 동쪽
				dice[0] = temp[3];
				dice[2] = temp[0];
				dice[3] = temp[5];
				dice[5] = temp[2];
				break;
			case 1: // 서쪽
				dice[0] = temp[2];
				dice[2] = temp[5];
				dice[3] = temp[0];
				dice[5] = temp[3];
				break;
			case 2: // 북쪽
				dice[0] = temp[4];
				dice[1] = temp[0];
				dice[4] = temp[5];
				dice[5] = temp[1];
				break;
			case 3: // 남쪽
				dice[0] = temp[1];
				dice[1] = temp[5];
				dice[4] = temp[0];
				dice[5] = temp[4];
				break;
		}
	}
}
