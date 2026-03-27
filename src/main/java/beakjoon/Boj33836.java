package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj33836 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int query = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < query; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long x = Long.parseLong(st.nextToken());
			long y = Long.parseLong(st.nextToken());

			if (x == 0 && y == 0) {
				sb.append(0).append("\n");
			} else if (y == 0 && x > 0) {
				sb.append(0).append("\n");
			} else if (x > 0 || (x == 0 && y != 0) || (x < 0 && y == 0) || (x < 0 && x == y)) {
				sb.append(1).append("\n");
			} else {
				sb.append(2).append("\n");
			}
		}
		System.out.println(sb);
	}
}
