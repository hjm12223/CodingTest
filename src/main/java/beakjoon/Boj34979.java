package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj34979 {
	static int[] moves = new int[] {-1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();
		int[][] arr = new int[4][N];
		int max = 0;
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int floor = Integer.parseInt(st.nextToken()) - 1;
				int classes = Integer.parseInt(st.nextToken()) - 1;
				for (int move : moves) {
					int nextMoves = move + classes;
					if (nextMoves < 0 || nextMoves >= N) continue;
					arr[floor][nextMoves]++;
					max = Math.max(arr[floor][nextMoves], max);
				}
				if (floor - 1 >= 0) {
					arr[floor - 1][classes]++;
					max = Math.max(arr[floor - 1][classes], max);
				}
				if (floor + 1 < 4) {
					arr[floor + 1][classes]++;
					max = Math.max(arr[floor + 1][classes], max);
				}
			} else {
				int floor = Integer.parseInt(st.nextToken()) - 1;
				int highDiscomfort = 0;
				int idx = 0;
				for (int j = 0; j < N; j++) {
					if (arr[floor][j] > highDiscomfort) {
						highDiscomfort = arr[floor][j];
						idx = j;
					}
				}
				idx++;
				sb.append(idx + "\n");
			}
		}
		boolean isFind = false;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == max) {
					sb.append((i + 1) + " " + (j + 1));
					isFind = true;
					break;
				}
			}
			if (isFind) break;
		}
		System.out.println(sb);
	}
}
/**
 또한 모든 쿼리를 수행한 후, 건물 전체에서 불쾌함이 가장 높은 반을 이달의 오량반으로 선정한다.
 불쾌함이 가장 높은 반이 여러 반인 경우, 층수가 더 낮은 층을 우선으로 하고, 층수까지 같다면 반 번호가 더 작은 반을 선택한다.
 */
