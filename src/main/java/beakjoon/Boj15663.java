package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15663 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

		Arrays.sort(arr);

		boolean[] visited = new boolean[N];
		int[] output = new int[M];

		permu(arr, output, 0, M, visited, "");

		bw.flush();
		bw.close();

	}

	private static void permu(int[] arr, int[] output, int depth, int r, boolean[] visited, String result) throws
		IOException {
		if (depth == r) {
			for (int j : output) {
				bw.write(j + " ");
			}
			bw.write("\n");
			return;
		}
		int lastUsed = -1;
		for (int i = 0; i < arr.length; i++) {
			if (!visited[i] && arr[i] != lastUsed) {
				output[depth] = arr[i];
				permu(arr, output, depth + 1, r, visited, result + output[depth]);
				lastUsed = arr[i];
			}
		}
	}
}
