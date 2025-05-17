package beakjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Boj20364 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int Q = Integer.parseInt(input[1]);

		boolean[] visited = new boolean[N + 1];

		for (int i = 0; i < Q; i++) {
			int target = Integer.parseInt(br.readLine());
			int curr = target;
			int blocked = 0;

			while (curr > 0) {
				if (visited[curr]) blocked = curr;
				curr /= 2;
			}

			if (blocked == 0) visited[target] = true;
			bw.write(blocked + "\n");
		}

		bw.flush();
		bw.close();
	}
}
