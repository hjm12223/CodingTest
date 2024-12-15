package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj5913 {
	static int result = 0; // 가능한 경로의 수
	static int[] dx = {0, 1, 0, -1}; // 상하좌우 이동
	static int[] dy = {1, 0, -1, 0};
	static boolean[][] visited; // 방문 여부
	static int[][] arr; // 맵 정보
	static int target = 25; // 방문해야 할 칸의 수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[5][5];
		visited = new boolean[5][5];
		int K = Integer.parseInt(br.readLine()); // 장애물 개수

		for (int i = 0; i < K; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken()) - 1;
			int col = Integer.parseInt(st.nextToken()) - 1;
			arr[row][col] = 1; // 장애물 위치 표시
		}

		// 초기 위치 설정
		visited[0][0] = true; // 준규의 시작 위치
		visited[4][4] = true; // 해빈의 시작 위치
		dfs(0, 0, 4, 4, 2); // 처음 두 칸을 방문했다고 가정하고 시작
		System.out.println(result);
	}

	private static void dfs(int junX, int junY, int haeX, int haeY, int cnt) {
		// 모든 칸을 방문하고 두 사람이 같은 위치에서 만난 경우
		if (cnt == target && junX == haeX && junY == haeY) {
			result++;
			return;
		}

		// 준규와 해빈의 움직임
		for (int jd = 0; jd < 4; jd++) { // 준규의 방향
			int nextJunX = junX + dx[jd];
			int nextJunY = junY + dy[jd];
			if (!isValid(nextJunX, nextJunY)) continue; // 준규의 이동 유효성 검사

			for (int hd = 0; hd < 4; hd++) { // 해빈의 방향
				int nextHeaX = haeX + dx[hd];
				int nextHeaY = haeY + dy[hd];
				if (!isValid(nextHeaX, nextHeaY)) continue; // 해빈의 이동 유효성 검사

				// 두 사람이 겹치는 경우는 허용하지 않음
				if (nextJunX == nextHeaX && nextJunY == nextHeaY) continue;

				// 방문 여부 확인 및 이동 처리
				visited[nextJunX][nextJunY] = true;
				visited[nextHeaX][nextHeaY] = true;
				dfs(nextJunX, nextJunY, nextHeaX, nextHeaY, cnt + 1); // 1칸씩 이동
				visited[nextJunX][nextJunY] = false;
				visited[nextHeaX][nextHeaY] = false;
			}
		}
	}

	private static boolean isValid(int x, int y) {
		// 격자 범위 내에 있고, 장애물이 없는 칸이며, 아직 방문하지 않은 경우
		return x >= 0 && y >= 0 && x < 5 && y < 5 && arr[x][y] == 0 && !visited[x][y];
	}
}
