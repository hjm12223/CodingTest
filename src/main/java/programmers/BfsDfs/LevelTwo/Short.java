package programmers.BfsDfs.LevelTwo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Short {
	static int[] dx = new int[]{1,0,-1,0};
	static int[] dy = new int[]{0,1,0,-1};

	static Queue<Node> q = new LinkedList<>();
	public static void main(String[] args) {
		int solution = solution(
			new int[][] {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1},{0,0,0,0,1}});
		System.out.println("solution = " + solution);
	}
	public static int solution(int[][] maps) {
		int n = maps.length;
		int m = maps[0].length;
		boolean[][] isVisited = new boolean[n][m];
		int[][] dist = new int[n][m];
		for (int i = 0 ; i < n ; i++){
			Arrays.fill(dist[i],Integer.MAX_VALUE);
		}
		dist[0][0] = 1;
		isVisited[0][0] = true;
		bfs(maps,n,m,dist,isVisited);

		if (dist[n-1][m-1] == Integer.MAX_VALUE){
			return -1;
		}else {
			return dist[n-1][m-1];
		}
	}
	private static void bfs(int[][] maps,int n , int m,int[][] dist,boolean[][] isVisited){
		q.offer(new Node(0,0));
		while (!q.isEmpty()){
			Node curr = q.poll();
			for (int k = 0 ; k < 4 ; k++){
				int nx = dx[k] + curr.row;
				int ny = dy[k] + curr.column;
				if ( nx < 0 || ny < 0 || nx >= m ||  ny >= n) continue;
				if (!isVisited[ny][nx] && maps[ny][nx] != 0){
					isVisited[ny][nx] = true;
					q.offer(new Node(ny,nx));
					if (dist[ny][nx] > dist[curr.column][curr.row]) {
						dist[ny][nx] = dist[curr.column][curr.row] + 1;
					}
				}
			}
		}
	}

	private static class Node{

		int column;
		int row;

		public Node(int column, int row) {
			this.column = column;
			this.row = row;
		}
	}
}
