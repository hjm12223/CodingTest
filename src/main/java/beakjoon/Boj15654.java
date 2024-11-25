package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15654 {
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		isVisited = new boolean[N];

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		Arrays.sort(arr);
		int[] out = new int[M];
		permu(arr, out, 0, M);
		bw.flush();
		bw.close();
	}

	private static void permu(int[] arr, int[] out, int depth, int r) throws IOException {
		if (depth == r) {
			for (int i : out) {
				bw.write(i + " ");
			}
			bw.write("\n");
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (!isVisited[i]) {
				isVisited[i] = true;
				out[depth] = arr[i];
				permu(arr, out, depth + 1, r);
				isVisited[i] = false;
			}
		}
	}
}
