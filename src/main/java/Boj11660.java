import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj11660 {
	static int[] dx = new int[] {1, 0, 0, -1};
	static int[] dy = new int[] {0, 1, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			int[] split = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 1; j <= N; j++) {
				arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + split[j - 1];
			}
		}
		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			int targetX = Integer.parseInt(st.nextToken());
			int targetY = Integer.parseInt(st.nextToken());
			int sum =
				arr[targetX][targetY] - arr[startX - 1][targetY] - arr[targetX][startY - 1] + arr[startX - 1][startY
					- 1];
			bw.write(sum + "\n");
		}
		bw.flush();
		bw.close();
	}

	private static int bfs(int startX, int startY, int targetX, int targetY, int[][] arr, int N) {
		boolean[][] isVisited = new boolean[N][N];
		isVisited[startX][startY] = true;
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] {startX, startY});
		int sum = arr[startX][startY];
		while (!q.isEmpty()) {
			int[] curr = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = curr[0] + dx[i];
				int nextY = curr[1] + dy[i];
				if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= N) continue;
				if (nextX < startX || nextY < startY || nextX > targetX || nextY > targetY) continue;
				if (!isVisited[nextX][nextY]) {
					isVisited[nextX][nextY] = true;
					sum += arr[nextX][nextY];
					q.offer(new int[] {nextX, nextY});
				}
			}
		}
		return sum;
	}
}
