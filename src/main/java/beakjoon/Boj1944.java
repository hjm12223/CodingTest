package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1944 {
	static int[][] moves = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
	static int[][] arr;
	static int N, M;
	static List<Node> points = new ArrayList<>();
	static List<List<Node>> graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][N];
		graph = new ArrayList<>();
		for (int i = 0; i <= M; i++) graph.add(new ArrayList<>());

		int index = 0;
		int[][] pointIndex = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				arr[i][j] = (line[j] == '1') ? 1 : 0;
				if (line[j] == 'S' || line[j] == 'K') {
					points.add(new Node(i, j, 0));
					pointIndex[i][j] = index++;
				}
			}
		}

		for (int i = 0; i < points.size(); i++) {
			int[] distances = bfs(points.get(i));
			for (int j = i + 1; j < points.size(); j++) {
				if (distances[j] > 0) {
					graph.get(i).add(new Node(j, distances[j]));
					graph.get(j).add(new Node(i, distances[j]));
				}
			}
		}

		int result = prim(0, points.size());
		System.out.println(result);
	}

	private static int[] bfs(Node start) {
		boolean[][] visited = new boolean[N][N];
		Queue<Node> q = new LinkedList<>();
		int[] distances = new int[points.size()];
		Arrays.fill(distances, -1);

		q.offer(new Node(start.x, start.y, 0));
		visited[start.x][start.y] = true;

		while (!q.isEmpty()) {
			Node curr = q.poll();
			int currIdx = getIndex(curr.x, curr.y);
			if (currIdx != -1) distances[currIdx] = curr.cost;

			for (int[] move : moves) {
				int nx = curr.x + move[0];
				int ny = curr.y + move[1];

				if (nx >= 0 && ny >= 0 && nx < N && ny < N && !visited[nx][ny] && arr[nx][ny] != 1) {
					visited[nx][ny] = true;
					q.offer(new Node(nx, ny, curr.cost + 1));
				}
			}
		}
		return distances;
	}

	private static int getIndex(int x, int y) {
		for (int i = 0; i < points.size(); i++) {
			if (points.get(i).x == x && points.get(i).y == y) return i;
		}
		return -1;
	}

	private static int prim(int start, int size) {
		boolean[] visited = new boolean[size];
		Queue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
		pq.offer(new Node(start, 0));

		int totalCost = 0, count = 0;

		while (!pq.isEmpty()) {
			Node curr = pq.poll();
			if (visited[curr.x]) continue;

			visited[curr.x] = true;
			totalCost += curr.cost;
			count++;

			for (Node next : graph.get(curr.x)) {
				if (!visited[next.x]) pq.offer(next);
			}
		}
		return (count == size) ? totalCost : -1;
	}

	static class Node {
		int x, y, cost;

		Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}

		Node(int x, int cost) {
			this.x = x;
			this.cost = cost;
		}
	}
}
