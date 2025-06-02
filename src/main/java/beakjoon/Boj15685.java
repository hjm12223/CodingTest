package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15685 {
	static int[][] moves = new int[][] {{0, 1}, {-1, 0}, {0, -1}, {1, 0}}; //0 우, 1 상, 2 좌, 3 하
	static int[][] arr;

	/*
	드래곤 커브는 3가지 속성으로 이루어짐
	1. 시작 점
	2. 시작 방향
	3. 세대
	길이가 1인 선분은 0세대 드래곤 커브
	1세대 드래곤 커브는 0세대 드래곤 커브의 끝점을 기준으로 시계 방향으로 90도 회전시킨 다음 0 세대 드래곤 커브끝점에 붙인것
	여기서 어떻게 연결을 할것인지
	그래프?
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[101][101];
		int N = Integer.parseInt(st.nextToken()); // 드래곤 커브의 개수
		while (N-- > 0) {
			int[] command = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int X = command[0];
			int Y = command[1];
			int dir = command[2];
			int generation = command[3];
			makeGeneration(Y, X, dir, generation);
		}
		int result = countDragon();
		System.out.println(result);
	}

	private static int countDragon() {
		int cnt = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] == 1 &&
					arr[i][j + 1] == 1 &&
					arr[i + 1][j] == 1 &&
					arr[i + 1][j + 1] == 1) {
					cnt++;
				}
			}
		}
		return cnt;
	}

	private static void makeGeneration(int x, int y, int dir, int generation) {

		List<Integer> directions = new ArrayList<>();
		directions.add(dir);
		for (int i = 0; i < generation; i++) {
			for (int j = directions.size() - 1; j >= 0; j--) {
				directions.add((directions.get(j) + 1) % 4);
			}
		}
		arr[x][y] = 1;
		for (int direction : directions) {
			x += moves[direction][0];
			y += moves[direction][1];
			if (x < 0 || y < 0 || x >= 101 || y >= 101) continue;
			arr[x][y] = 1;
		}
	}
}
