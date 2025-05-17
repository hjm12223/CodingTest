package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Boj2887 {
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Planet[] planets = new Planet[N];
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			Planet planet = new Planet(i, x, y, z);
			planets[i] = planet;
		}

		List<Edge> list = new ArrayList<>();
		Arrays.sort(planets, (o1, o2) -> o1.x - o2.x);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(planets[i].x - planets[i + 1].x);
			list.add(new Edge(planets[i].index, planets[i + 1].index, cost));
		}

		Arrays.sort(planets, (o1, o2) -> o1.y - o2.y);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(planets[i].y - planets[i + 1].y);
			list.add(new Edge(planets[i].index, planets[i + 1].index, cost));
		}

		Arrays.sort(planets, (o1, o2) -> o1.z - o2.z);
		for (int i = 0; i < N - 1; i++) {
			int cost = Math.abs(planets[i].z - planets[i + 1].z);
			list.add(new Edge(planets[i].index, planets[i + 1].index, cost));
		}

		Collections.sort(list);
		int totalCost = 0;
		for (Edge edge : list) {
			if (find(edge.from) != find(edge.to)) {
				union(edge.from, edge.to);
				totalCost += edge.cost;
			}
		}
		System.out.println(totalCost);
	}

	static int find(int x) {
		if (x != parents[x]) {
			parents[x] = find(parents[x]);
		}
		return parents[x];
	}

	static void union(int a, int b) {
		a = parents[a];
		b = parents[b];
		if (a != b) {
			parents[b] = a;
		}
	}

	private static class Planet {
		int index;
		int x;
		int y;
		int z;

		public Planet(int index, int x, int y, int z) {
			this.index = index;
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}

	static class Edge implements Comparable<Edge> {
		int from, to, cost;

		public Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}
}