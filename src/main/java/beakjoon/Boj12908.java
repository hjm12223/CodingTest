// package beakjoon;
//
// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStreamReader;
// import java.util.ArrayDeque;
// import java.util.Map;
// import java.util.Queue;
// import java.util.StringTokenizer;
// import java.util.TreeMap;
//
// public class Boj12908 {
//
// 	public static void main(String[] args) throws IOException {
// 		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// 		StringTokenizer st = new StringTokenizer(br.readLine());
//
// 		int xs = Integer.parseInt(st.nextToken());
// 		int ys = Integer.parseInt(st.nextToken());
//
// 		st = new StringTokenizer(br.readLine());
//
// 		int xe = Integer.parseInt(st.nextToken());
// 		int ye = Integer.parseInt(st.nextToken());
// 		int distance = Math.abs(xe - xs) + Math.abs(ye - ys);
// 		Map<String, int[]> teleport = new TreeMap<>();
// 		for (int i = 0; i < 3; i++) {
//
// 			st = new StringTokenizer(br.readLine());
// 			int sx = Integer.parseInt(st.nextToken());
// 			int sy = Integer.parseInt(st.nextToken());
//
// 			int ex = Integer.parseInt(st.nextToken());
// 			int ey = Integer.parseInt(st.nextToken());
//
// 			teleport.put(sx + "_" + sy, new int[] {ex, ey});
// 			teleport.put(ex + "_" + ey, new int[] {sx, sy});
// 		}
//
// 		Queue<int[]> q = new ArrayDeque<>();
// 		q.offer(new int[] {xs, ys, 0});
//
// 		boolean[][] visited = new boolean[xe * 2][ye * 2];
// 		visited[xs][ys] = true;
//
// 		while (!q.isEmpty()) {
// 			int[] curr = q.poll();
// 			if (curr[0] == xe && curr[1] == ye) {
// 				System.out.println(curr[2]);
// 				return;
// 			}
// 			if (teleport.containsKey(curr[0] + "_" + curr[1])) {
// 				int[] next = teleport.get(curr[0] + "_" + curr[1]);
// 				if (!visited[next[0]][next[1]]) {
// 					q.offer(new int[] {next[0], next[1], curr[2] + 10});
// 					visited[next[0]][next[1]] = true;
// 				}
// 			}
// 			for (int[] move : moves) {
// 				int nx = curr[0] + move[0];
// 				int ny = curr[1] + move[1];
// 				if (nx < 0 || ny < 0 || nx >= xe * 2 || ny >= ye * 2) continue;
// 				if (!visited[nx][ny]) {
// 					visited[nx][ny] = true;
// 					q.offer(new int[] {nx, ny, curr[2] + 1});
// 				}
// 			}
// 		}
// 	}
// }
//
// /*
// 제일 처음에 수빈이의 위치는 (xs, ys)이고,
// 집이 위치한 (xe, ye)로 이동하려고 한다.
//
// 수빈이는 두 가지 방법으로 이동할 수 있다. 첫 번째 방법은 점프를 하는 것이다.
// 예를 들어 (x, y)에 있는 경우에 (x+1, y), (x-1, y), (x, y+1), (x, y-1)로 이동할 수 있다.
// 점프는 1초가 걸린다.
//
// 두 번째 방법은 텔레포트를 사용하는 것이다.
// 텔레포트를 할 수 있는 방법은 총 세 가지가 있으며,
// 미리 정해져 있다. 텔레포트는 네 좌표 (x1, y1), (x2, y2)로 나타낼 수 있으며,
// (x1, y1)에서 (x2, y2)로 또는 (x2, y2)에서 (x1, y1)로 이동할 수 있다는 것이다.
// 텔레포트는 10초가 걸린다.
//
// 수빈이의 위치와 집의 위치가 주어졌을 때,
// 집에 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.
//  */
