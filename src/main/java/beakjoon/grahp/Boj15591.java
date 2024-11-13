package beakjoon.grahp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15591 {
	static boolean[] isVisited;
	static List<List<Usado>> graph;
	static int N;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken()); // 퀘션

		graph = new ArrayList<>();

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		for (int i = 0; i < N - 1; i++) { // N-1 개의 동영상 쌍
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			graph.get(start).add(new Usado(end, value));
			graph.get(end).add(new Usado(start, value));
		}
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine());
			int usado = Integer.parseInt(st.nextToken());
			int vertex = Integer.parseInt(st.nextToken());
			dijkstra(usado, vertex);
		}
		bw.flush();
		bw.close();

	}

	private static void dijkstra(int usado, int vertex) throws IOException {
		isVisited = new boolean[N + 1];
		int result = 0;
		Queue<Usado> pq = new PriorityQueue<>();
		pq.offer(new Usado(vertex, 0));

		while (!pq.isEmpty()) {
			Usado curr = pq.poll();
			if (isVisited[curr.current]) continue;
			isVisited[curr.current] = true;
			for (int i = 0; i < graph.get(curr.current).size(); i++) {
				Usado nextUsado = graph.get(curr.current).get(i);
				if (isVisited[nextUsado.current]) continue;
				if (nextUsado.value >= usado) {
					pq.offer(new Usado(nextUsado.current, nextUsado.value));
					result++;
				}
			}
		}
		bw.write(result + "\n");
	}

	private static class Usado implements Comparable<Usado> {
		int current;
		int value;

		public Usado(int current, int value) {
			this.current = current;
			this.value = value;
		}

		@Override
		public int compareTo(Usado o) {
			return this.value - o.value;
		}
	}
}
