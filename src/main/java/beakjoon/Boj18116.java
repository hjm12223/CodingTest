package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj18116 {
	static int[] parent = new int[1000001];
	static int[] sizes = new int[1000001];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 공통부모를 찾는 문제 해당 공통부모에 몇개의 정수가 존재하는지를 판별하면된다
		// 그럼 Union Find 를 통해서 찾을 수 있다
		StringTokenizer st = new StringTokenizer(br.readLine());
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= 1000000; i++) {
			parent[i] = i;
			sizes[i] = 1;
		}
		int N = Integer.parseInt(st.nextToken()); // 명령어의 갯수
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("I")) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			} else {
				int c = Integer.parseInt(st.nextToken());
				bw.write(sizes[find(c)] + "\n");
			}
		}
		bw.flush();
		bw.close();
	}

	private static int find(int a) {
		if (a != parent[a]) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}

	private static void union(int a, int b) {
		int parentA = parent[a];
		int parentB = parent[b];
		if (parentA != parentB) {
			if (sizes[parentA] < sizes[parentB]) {
				int temp = parentA;
				parentA = parentB;
				parentB = temp;
			}
			parent[parentB] = parentA;
			sizes[parentA] += sizes[parentB];
		}
	}
}